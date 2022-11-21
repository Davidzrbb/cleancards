package arch.hex.domain.ports.server;

import arch.hex.domain.functional.model.CardsPack;
import java.util.UUID;

public interface CardsPackPersistenceSpi extends PersistenceSpi<CardsPack, UUID> {
}

