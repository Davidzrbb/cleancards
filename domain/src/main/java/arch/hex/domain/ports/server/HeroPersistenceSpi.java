package arch.hex.domain.ports.server;

import arch.hex.domain.functional.model.Hero;

import java.util.UUID;

public interface HeroPersistenceSpi extends PersistenceSpi<Hero, UUID> {
}
