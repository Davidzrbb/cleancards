package validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.functional.service.validation.FightValidator;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RequiredArgsConstructor
class FightValidatorTest {

   /* @Mock
    private DeckFinderByIdService deckFinderByIdService;

    @InjectMocks
    private FightValidator fightValidator;

    private String idAlly = "1";
    private String idEnemy = "2";
    private Deck deckAlly;
    private Deck deckEnemy;
    private Hero heroAlly;
    private Hero heroEnemy;
    private Player playerAlly;
    private Player playerEnemy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        heroAlly = new Hero("Hero Ally", 1);
        heroEnemy = new Hero("Hero Enemy", 2);
        playerAlly = new Player("Player Ally");
        playerEnemy = new Player("Player Enemy");

        deckAlly = new Deck("Deck Ally", heroAlly, playerAlly);
        deckEnemy = new Deck("Deck Enemy", heroEnemy, playerEnemy);
    }

    @Test
    public void shouldReturnInvalidWhenDeckNotFound() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Optional.empty());
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Optional.empty());

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Deck not found");
    }

    @Test
    public void shouldReturnInvalidWhenHeroNotFound() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Optional.ofNullable(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Optional.ofNullable(deckEnemy));

        deckAlly.setHero(null);
        deckEnemy.setHero(null);

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Hero not found");
    }

    @Test
    public void shouldReturnInvalidWhenHeroLevelIsTooHigh() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Optional.ofNullable(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Optional.ofNullable(deckEnemy));

        heroAlly.setLevel(3);
        heroEnemy.setLevel(2);

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Hero level is too high");
    }

    @Test
    public void shouldReturnInvalidWhenPlayerNotFound() {
        when(deckFinderByIdService.findById(idAlly)).thenReturn(Optional.ofNullable(deckAlly));
        when(deckFinderByIdService.findById(idEnemy)).thenReturn(Optional.ofNullable(deckEnemy));

        deckAlly.setPlayer(null);
        deckEnemy.setPlayer(null);

        Validation<ApplicationError, List<Deck>> result = fightValidator.validateFight(idAlly, idEnemy);

        assertEquals(result.isInvalid(), true);
        assertEquals(result.getError().context(), "Player not found");
    }*/
}