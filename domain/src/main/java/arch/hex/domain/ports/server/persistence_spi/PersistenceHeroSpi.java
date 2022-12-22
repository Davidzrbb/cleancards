package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;

public interface PersistenceHeroSpi<T, RARITY> {
    Either<ApplicationError, T> save(T o);

    Option<List<T>> findAll();

    Option<T> findByRarity(RARITY rarity);
}
