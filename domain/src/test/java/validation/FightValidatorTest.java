package validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.functional.service.validation.FightValidator;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FightValidatorTest {

    @Mock
    private DeckFinderByIdService deckFinderByIdService;

    @InjectMocks
    private FightValidator fightValidator;
    String idAlly = "idAlly";
    String idEnemy = "idEnemy";

    @Test
    void should_return_invalid_when_deck_not_found() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.none());
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.none());

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertThat(result).containsInvalidInstanceOf(ApplicationError.class);
        assertEquals("Deck not found", result.getError().context());
    }

    @Test
    void should_return_invalid_when_hero_not_found() {
        Deck deckAlly = Deck.builder().idDeck(idAlly).hero(null).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).hero(null).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(true,result.isInvalid());
        assertEquals( "Hero not found", result.getError().context());
    }

    @Test
    void should_return_invalid_when_hero_level_is_too_high() {
        Hero heroAlly = Hero.builder().level(3).build();
        Hero heroEnemy = Hero.builder().level(2).build();
        Deck deckAlly = Deck.builder().idDeck(idAlly).hero(heroAlly).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).hero(heroEnemy).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(true, result.isInvalid());
        assertEquals("Hero level is too high", result.getError().context());
    }

    @Test
    void should_return_invalid_when_player_not_found() {
        Hero heroAlly = Hero.builder().level(1).build();
        Hero heroEnemy = Hero.builder().level(1).build();
        Deck deckAlly = Deck.builder().idDeck(idAlly).hero(heroAlly).player(null).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).hero(heroEnemy).player(null).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(true, result.isInvalid());
        assertEquals("Player not found", result.getError().context());
    }
}