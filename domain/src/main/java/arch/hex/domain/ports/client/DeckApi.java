package arch.hex.domain.ports.client;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import io.vavr.control.Either;

import java.util.UUID;

public interface DeckApi {
    Either<ApplicationError, Deck> create(Player player);
}
