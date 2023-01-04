package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;

public interface PersistenceFightSpi<T, ID> {
    Either<ApplicationError, T> save(T o);

    List<T> findByIdHero(ID id);
}
