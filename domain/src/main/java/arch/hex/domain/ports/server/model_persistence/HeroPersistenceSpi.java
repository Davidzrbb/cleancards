package arch.hex.domain.ports.server.model_persistence;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.server.persistence_spi.PersistenceHeroSpi;

public interface HeroPersistenceSpi extends PersistenceHeroSpi<Hero, Rarity> {
}
