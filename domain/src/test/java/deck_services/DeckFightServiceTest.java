package deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFightService;
import arch.hex.domain.functional.service.fight_services.FightCreatorService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateHpService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.functional.service.validation.FightValidator;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void should_get_hero_winner_of_fight() {

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
        Hero heroWinner = Hero.builder().build();
        Hero heroAlly = Hero.builder().build();

        when(playerUpdateWinnerService.updatePlayerWinner()).thenReturn(Either.right(playerAlly));
    }

    @Test
    void should_save_history_fight() {
        Hero heroAlly = Hero.builder().build();
        Hero heroEnemy = Hero.builder().build();

        // TODO
    }
}
