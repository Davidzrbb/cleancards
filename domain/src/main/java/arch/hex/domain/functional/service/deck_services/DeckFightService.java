package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.fight_services.FightCreatorService;
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
    private final FightCreatorService fightCreatorService;

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

        Hero heroWinner = getHeroWinnerOfFight(heroAlly, heroEnemy);
        Either<ApplicationError, Hero> heroWinnerUpdate = updateExperienceForWinningFight(heroWinner);
        if (heroWinnerUpdate.isLeft()) {
            return Either.left(heroWinnerUpdate.getLeft());
        }
        Either<ApplicationError, Player> playerWinnerUpdate = updatePlayerWinner(playerAlly, playerEnemy, heroWinner, heroAlly);
        if (playerWinnerUpdate.isLeft()) {
            return Either.left(playerWinnerUpdate.getLeft());
        }
        Either<ApplicationError, Fight> fight = saveHistoryFight(heroAlly, heroEnemy, heroWinner);
        if (fight.isLeft()) {
            return Either.left(fight.getLeft());
        }

        return Either.right("The winner is " + playerWinnerUpdate.get().getPseudo());
    }

    public Hero getHeroWinnerOfFight(Hero heroAlly, Hero heroEnemy) {
        Hero heroWinner = heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy);
        return heroAlly.getIdHero().equals(heroWinner.getIdHero()) ? heroAlly : heroEnemy;
    }

    public Either<ApplicationError, Hero> updateExperienceForWinningFight(Hero heroWinner) {
        return heroUpdateExperienceService.updateExperienceForWinningFight(heroWinner);
    }

    public Either<ApplicationError, Player> updatePlayerWinner(Player playerAlly, Player playerEnemy, Hero heroWinner, Hero heroAlly) {
        Player playerWinner = heroWinner.getIdHero().equals(heroAlly.getIdHero()) ? playerAlly : playerEnemy;
        return playerUpdateWinnerService.updatePlayerWinner(playerWinner);
    }

    public Either<ApplicationError, Fight> saveHistoryFight(Hero heroAlly, Hero heroEnemy, Hero heroWinner) {
        return fightCreatorService.create(Fight.builder()
                .heroAlly(heroAlly)
                .heroEnemy(heroEnemy)
                .allyWin(heroWinner.getIdHero().equals(heroAlly.getIdHero()))
                .build());
    }
}
