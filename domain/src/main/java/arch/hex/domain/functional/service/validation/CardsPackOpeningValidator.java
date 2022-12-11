package arch.hex.domain.functional.service.validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Player;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public class CardsPackOpeningValidator {

    public Validation<ApplicationError, Boolean> validate(Option<CardsPack> cardsPack, Option<Player> player) {
        if (cardsPack.isEmpty()) {
            return Validation.invalid(new ApplicationError("CardsPack not found", null, null, null));
        }
        if (player.isEmpty()) {
            return Validation.invalid(new ApplicationError("Player not found", null, null, null));
        }
        if (player.get().getTokens() < cardsPack.get().getRequiredTokens()) {
            return Validation.invalid(new ApplicationError("Not enough coins", null, null, null));
        }
        return Validation.valid(true);
    }
}
