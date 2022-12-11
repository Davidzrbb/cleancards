package arch.hex.domain.ports.client.player_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import io.vavr.control.Either;

public interface PlayerCreatorApi {
    Either<ApplicationError, Player> create(String pseudo);
}
