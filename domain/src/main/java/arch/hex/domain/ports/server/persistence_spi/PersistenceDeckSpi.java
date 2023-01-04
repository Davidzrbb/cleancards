package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;

public interface PersistenceDeckSpi<T> {
    Either<ApplicationError, T> save(T o);

    List<Deck> findByIdPlayer(String player);

    Option<Deck> findById(String idDeck);
}
