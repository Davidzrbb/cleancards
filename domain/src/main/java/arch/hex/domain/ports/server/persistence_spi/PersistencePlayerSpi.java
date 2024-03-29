package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistencePlayerSpi<T, ID> {
    Either<ApplicationError, T> save(T o);

    Option<T> findById(ID id);

}
