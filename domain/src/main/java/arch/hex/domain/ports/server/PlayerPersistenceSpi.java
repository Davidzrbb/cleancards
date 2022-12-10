package arch.hex.domain.ports.server;

import arch.hex.domain.functional.model.Player;

import java.util.UUID;

public interface PlayerPersistenceSpi extends PersistenceSpi<Player, UUID> {
}

