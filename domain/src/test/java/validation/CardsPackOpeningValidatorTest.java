package validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.validation.CardsPackOpeningValidator;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardsPackOpeningValidatorTest {

    @Mock
    private CardsPack cardsPack;

    @Mock
    private Player player;

    @InjectMocks private CardsPackOpeningValidator validator;

    @Test
    void validate_should_return_valid() {
        when(cardsPack.getRequiredTokens()).thenReturn(5);
        when(player.getTokens()).thenReturn(10);

        Validation<ApplicationError, Boolean> result = validator.validate(Option.of(cardsPack), Option.of(player));
        assertTrue(result.isValid());
    }

    @Test
    void validate_should_return_invalid_when_cards_pack_is_empty() {
        Validation<ApplicationError, Boolean> result = validator.validate(Option.none(), Option.of(player));
        assertFalse(result.isValid());
        ApplicationError error = result.getError();
        assertEquals("CardsPack not found", error.context());
    }

    @Test
    void validate_should_return_invalid_when_player_is_empty() {
        Validation<ApplicationError, Boolean> result = validator.validate(Option.of(cardsPack), Option.none());
        assertFalse(result.isValid());
        ApplicationError error = result.getError();
        assertEquals("Player not found", error.context());
    }

    @Test
    void validate_should_return_invalid_when_not_enough_coins() {
        when(cardsPack.getRequiredTokens()).thenReturn(10);
        when(player.getTokens()).thenReturn(5);

        Validation<ApplicationError, Boolean> result = validator.validate(Option.of(cardsPack), Option.of(player));

        assertFalse(result.isValid());
        ApplicationError error = result.getError();
        assertEquals("Not enough coins", error.context());
    }
}
