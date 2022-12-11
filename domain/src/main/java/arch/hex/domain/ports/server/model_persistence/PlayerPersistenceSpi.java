package arch.hex.domain.ports.server.model_persistence;

import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.server.persistence_spi.PersistencePlayerSpi;

import java.util.UUID;

public interface PlayerPersistenceSpi extends PersistencePlayerSpi<Player, String> {
}

