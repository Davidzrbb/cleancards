package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistenceHeroSpi<T, RARITY> {
    Either<ApplicationError, T> save(T o);

    Option<Set<T>> findAll();

    Option<T> findByRarity(RARITY rarity);
}
