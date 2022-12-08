package arch.hex.domain.functional.service.validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import io.vavr.control.Validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;


public interface CardsPackTypeValidator {
    static Validation<ApplicationError, CardsPackType> validate(CardsPackType cardsPackType) {
        return cardsPackType == CardsPackType.Argent || cardsPackType == CardsPackType.Diamond
                ? Valid(cardsPackType)
                : Invalid(new ApplicationError("Invalid CardsPack Type", null, cardsPackType, null));
    }
}

