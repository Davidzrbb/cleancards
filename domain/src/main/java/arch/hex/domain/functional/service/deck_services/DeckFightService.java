package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateHpService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.functional.service.validation.FightValidator;
import arch.hex.domain.ports.client.deck_api.DeckFightApi;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DeckFightService implements DeckFightApi {
    private final FightValidator fightValidator;
    private final HeroUpdateHpService heroUpdateHpService;
    private final HeroUpdateExperienceService heroUpdateExperienceService;
    private final PlayerUpdateWinnerService playerUpdateWinnerService;

    public Either<ApplicationError, String> fight(String idDeckAlly, String idDeckEnemy) {
        Validation<ApplicationError, List<Deck>> decks = fightValidator.validateFight(idDeckAlly, idDeckEnemy);

        if (decks.isInvalid()) {
            return Either.left(decks.getError());
        }
        Deck deckAlly = decks.get().get(0);
        Deck deckEnemy = decks.get().get(1);
        Hero heroAlly = deckAlly.getHero();
        Player playerAlly = deckAlly.getPlayer();
        Hero heroEnemy = deckEnemy.getHero();
        Player playerEnemy = deckEnemy.getPlayer();

        return getWinnerOfFight(heroAlly, heroEnemy, playerAlly, playerEnemy);
    }

    Either<ApplicationError, String> getWinnerOfFight(Hero heroAlly, Hero heroEnemy, Player playerAlly, Player playerEnemy) {
        Hero heroWinner = heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy);
        heroWinner = heroAlly.getIdHero().equals(heroWinner.getIdHero()) ? heroAlly : heroEnemy;
        Either<ApplicationError, Hero> heroWinnerUpdate = heroUpdateExperienceService.getExperienceForWinningFight(heroWinner);
        if (heroWinnerUpdate.isLeft()) {
            return Either.left(heroWinnerUpdate.getLeft());
        }
        Player playerWinner = heroWinner.getIdHero().equals(heroAlly.getIdHero()) ? playerAlly : playerEnemy;
        Either<ApplicationError, Player> playerWinnerUpdate = playerUpdateWinnerService.updatePlayerWinner(playerWinner);
        if (playerWinnerUpdate.isLeft()) {
            return Either.left(playerWinnerUpdate.getLeft());
        }

        return Either.right("The winner is " + playerWinnerUpdate.get().getPseudo());
    }
}
