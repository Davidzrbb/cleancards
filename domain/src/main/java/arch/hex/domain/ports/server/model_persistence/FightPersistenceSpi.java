package arch.hex.domain.ports.server.model_persistence;

import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.ports.server.persistence_spi.PersistenceFightSpi;

public interface FightPersistenceSpi extends PersistenceFightSpi<Fight, String> {
}

