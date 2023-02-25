package validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.functional.service.validation.FightValidator;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    public void shouldReturnInvalidWhenDeckNotFound() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.none());
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.none());

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Deck not found");
    }

    @Test
    public void shouldReturnInvalidWhenHeroNotFound() {
        Deck deckAlly = Deck.builder().idDeck(idAlly).hero(null).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).hero(null).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Hero not found");
    }

    @Test
    public void shouldReturnInvalidWhenHeroLevelIsTooHigh() {
        Hero heroAlly = Hero.builder().level(3).build();
        Hero heroEnemy = Hero.builder().level(2).build();
        Deck deckAlly = Deck.builder().idDeck(idAlly).hero(heroAlly).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).hero(heroEnemy).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Hero level is too high");
    }

    @Test
    public void shouldReturnInvalidWhenPlayerNotFound() {
        Deck deckAlly = Deck.builder().idDeck(idAlly).player(null).build();
        Deck deckEnemy = Deck.builder().idDeck(idEnemy).player(null).build();
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Option.of(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Option.of(deckEnemy));

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Player not found");
    }
}