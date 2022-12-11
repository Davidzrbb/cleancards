package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import io.vavr.control.Either;

public interface DeckCreatorApi {
    Either<ApplicationError, Deck> save(Player player);
}
