package arch.hex.domain.ports.client;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import io.vavr.control.Either;


public interface CardsPackApi {
    Either<ApplicationError, CardsPack> create(CardsPackType cardsPackType);
}
