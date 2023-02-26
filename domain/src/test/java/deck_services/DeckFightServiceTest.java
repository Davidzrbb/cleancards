package deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFightService;
import arch.hex.domain.functional.service.fight_services.FightCreatorService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateHpService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.functional.service.validation.FightValidator;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckFightServiceTest {
    @Mock
    private FightValidator fightValidator;

    @Mock
    private HeroUpdateHpService heroUpdateHpService;

    @Mock
    private HeroUpdateExperienceService heroUpdateExperienceService;

    @Mock
    private PlayerUpdateWinnerService playerUpdateWinnerService;

    @Mock
    private FightCreatorService fightCreatorService;

    @InjectMocks
    private DeckFightService deckFightService;

    @Test
    void should_return_error_when_deck_is_invalid() {
        String idDeckAlly = "idDeckAlly";
        String idDeckEnemy = "idDeckEnemy";
        ApplicationError error = new ApplicationError("Deck not found", null, null,null);

        when(fightValidator.validateFight(idDeckAlly, idDeckEnemy)).thenReturn(Validation.invalid(error));

        Either<ApplicationError, String> result = deckFightService.fight(idDeckAlly, idDeckEnemy);

        assertEquals("Deck not found", result.getLeft().context());
    }

    @Test
    void should_return_error_when_updateExperienceForWinningFight_returns_error() {

        String idDeckAlly = "idDeckAlly";
        String idDeckEnemy = "idDeckEnemy";
        Hero heroAlly = Hero.builder().idHero("heroAlly").build();
        Hero heroEnemy = Hero.builder().idHero("heroEnemy").build();
        Deck deckAlly = Deck.builder().hero(heroAlly).idDeck(idDeckAlly).build();
        Deck deckEnemy = Deck.builder().hero(heroEnemy).idDeck(idDeckEnemy).build();
        ApplicationError error = new ApplicationError("Hero not found", null, null,null);

        when(fightValidator.validateFight(idDeckAlly, idDeckEnemy)).thenReturn(Validation.valid(List.of(deckAlly, deckEnemy)));
        when(heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy)).thenReturn(heroAlly);
        when(heroUpdateExperienceService.updateExperienceForWinningFight(heroAlly)).thenReturn(Either.left(error));

        Either<ApplicationError, String> result = deckFightService.fight(idDeckAlly, idDeckEnemy);

        assertEquals("Hero not found", result.getLeft().context());
    }

    @Test
    void should_return_error_when_updatePlayerWinner_returns_error() {
        String idDeckAlly = "idDeckAlly";
        String idDeckEnemy = "idDeckEnemy";
        Hero heroAlly = Hero.builder().idHero("heroAlly").build();
        Hero heroEnemy = Hero.builder().idHero("heroEnemy").build();
        Deck deckAlly = Deck.builder().hero(heroAlly).idDeck(idDeckAlly).build();
        Deck deckEnemy = Deck.builder().hero(heroEnemy).idDeck(idDeckEnemy).build();
        ApplicationError error = new ApplicationError(null, null, null,null);

        when(fightValidator.validateFight(idDeckAlly, idDeckEnemy)).thenReturn(Validation.valid(List.of(deckAlly, deckEnemy)));
        when(heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy)).thenReturn(heroAlly);
        when(heroUpdateExperienceService.updateExperienceForWinningFight(heroAlly)).thenReturn(Either.right(heroAlly));
        when(playerUpdateWinnerService.updatePlayerWinner(null)).thenReturn(Either.left(error));

        Either<ApplicationError, String> result = deckFightService.fight(idDeckAlly, idDeckEnemy);

        assertEquals(error.context(), result.getLeft().context());
    }

    @Test
    void should_return_the_winner(){
        String idDeckAlly = "idDeckAlly";
        String idDeckEnemy = "idDeckEnemy";
        Hero heroAlly = Hero.builder().idHero("heroAlly").build();
        Hero heroEnemy = Hero.builder().idHero("heroEnemy").build();
        Hero heroWinner = Hero.builder().build();
        Fight fight = Fight.builder()
                .heroAlly(heroAlly)
                .heroEnemy(heroEnemy)
                .allyWin(heroWinner.getIdHero().equals(heroAlly.getIdHero()))
                .build();
        Deck deckAlly = Deck.builder().hero(heroAlly).idDeck(idDeckAlly).build();
        Deck deckEnemy = Deck.builder().hero(heroEnemy).idDeck(idDeckEnemy).build();
        Player playerAlly = Player.builder().winCount(0).build();


        when(fightValidator.validateFight(idDeckAlly, idDeckEnemy)).thenReturn(Validation.valid(List.of(deckAlly, deckEnemy)));
        when(heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy)).thenReturn(heroAlly);
        when(heroUpdateExperienceService.updateExperienceForWinningFight(heroAlly)).thenReturn(Either.right(heroAlly));
        when(playerUpdateWinnerService.updatePlayerWinner(null)).thenReturn(Either.right(playerAlly));
        when(fightCreatorService.create(any(Fight.class))).thenReturn(Either.right(fight));

        Either<ApplicationError, String> result = deckFightService.fight(idDeckAlly, idDeckEnemy);

        assertTrue(result.isRight());
    }

    @Test
    void should_return_error_when_saveHistoryFight_returns_error() {
        String idDeckAlly = "idDeckAlly";
        String idDeckEnemy = "idDeckEnemy";
        Hero heroAlly = Hero.builder().idHero("heroAlly").build();
        Hero heroEnemy = Hero.builder().idHero("heroEnemy").build();
        Hero heroWinner = Hero.builder().build();
        Deck deckAlly = Deck.builder().hero(heroAlly).idDeck(idDeckAlly).build();
        Deck deckEnemy = Deck.builder().hero(heroEnemy).idDeck(idDeckEnemy).build();
        Player playerAlly = Player.builder().winCount(0).build();
        ApplicationError error = new ApplicationError(null, null, null,null);

        when(fightValidator.validateFight(idDeckAlly, idDeckEnemy)).thenReturn(Validation.valid(List.of(deckAlly, deckEnemy)));
        when(heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy)).thenReturn(heroAlly);
        when(heroUpdateExperienceService.updateExperienceForWinningFight(heroAlly)).thenReturn(Either.right(heroAlly));
        when(playerUpdateWinnerService.updatePlayerWinner(null)).thenReturn(Either.right(playerAlly));
        when(fightCreatorService.create(any(Fight.class))).thenReturn(Either.left(error));

        Either<ApplicationError, String> result = deckFightService.fight(idDeckAlly, idDeckEnemy);

        assertEquals(error.context(), result.getLeft().context());
    }

    @Test
    void should_get_hero_winner_of_fight() {
        Hero heroAlly = Hero.builder().build();
        Hero heroEnemy = Hero.builder().build();
        when(heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy)).thenReturn(heroAlly);

        Hero result = deckFightService.getHeroWinnerOfFight(heroAlly, heroEnemy);

        assertEquals(heroAlly, result);
    }

    @Test
    void should_update_experience_for_winning_fight() {
        Hero heroWinner = Hero.builder().xp(0).build();

        when(heroUpdateExperienceService.updateExperienceForWinningFight(heroWinner)).thenReturn(Either.right(heroWinner.withXp(1)));

        Either<ApplicationError, Hero> result = deckFightService.updateExperienceForWinningFight(heroWinner);

        assertEquals(heroWinner.withXp(1), result.get());
    }

    @Test
    void should_update_player_winner() {
        Player playerAlly = Player.builder().winCount(0).build();
        Player playerEnemy = Player.builder().winCount(0).build();
        Hero heroWinner = Hero.builder().idHero("win").build();
        Hero heroAlly = Hero.builder().idHero("loose").build();
        when(playerUpdateWinnerService.updatePlayerWinner(playerEnemy)).thenReturn(Either.right(playerEnemy.withWinCount(1)));

        Either<ApplicationError, Player> result = deckFightService.updatePlayerWinner(playerAlly, playerEnemy, heroWinner, heroAlly);

        assertEquals(playerEnemy.withWinCount(1), result.get());
    }

    @Test
    void should_save_history_fight() {
        Hero heroAlly = Hero.builder().build();
        Hero heroEnemy = Hero.builder().build();
        Hero heroWinner = Hero.builder().build();
        Fight fight = Fight.builder()
                .heroAlly(heroAlly)
                .heroEnemy(heroEnemy)
                .allyWin(heroWinner.getIdHero().equals(heroAlly.getIdHero()))
                .build();

        when(fightCreatorService.create(any(Fight.class))).thenReturn(Either.right(fight));

        Either<ApplicationError, Fight> result = deckFightService.saveHistoryFight(heroAlly, heroEnemy, heroWinner);

        assertEquals(fight, result.get());
    }
}
