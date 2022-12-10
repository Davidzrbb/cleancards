package arch.hex.domain.ports.client;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Player;
import io.vavr.control.Either;

public interface PlayerApi {
    Either<ApplicationError, Player> create(String pseudo);
}
