package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistenceCardsPackSpi<T,ID> {
    Either<ApplicationError, T> save(T o);

    Option<T> findById(ID id);
}
