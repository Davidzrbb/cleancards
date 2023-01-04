package arch.hex.domain.ports.server.persistence_spi;

import arch.hex.domain.ApplicationError;

import arch.hex.domain.functional.model.Hero;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;

public interface PersistenceHeroSpi<T, RARITY> {
    Either<ApplicationError, T> save(T o);

    List<Hero> findAll();

    Option<T> findByRarity(RARITY rarity);
}
