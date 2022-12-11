package arch.hex.domain.ports.server.model_persistence;

import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.ports.server.persistence_spi.PersistenceCardsPackSpi;

import java.util.UUID;


public interface CardsPackPersistenceSpi extends PersistenceCardsPackSpi<CardsPack, String> {
}

