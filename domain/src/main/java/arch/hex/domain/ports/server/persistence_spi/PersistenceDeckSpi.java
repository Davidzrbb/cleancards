package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistenceDeckSpi<T> {
    Either<ApplicationError, T> save(T o);

    Option<Set<Deck>> findByPlayer(Player player);
}
