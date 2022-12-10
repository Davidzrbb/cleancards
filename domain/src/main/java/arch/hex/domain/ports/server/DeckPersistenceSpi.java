package arch.hex.domain.ports.server;

import arch.hex.domain.functional.model.Deck;

import java.util.UUID;

public interface DeckPersistenceSpi extends PersistenceSpi<Deck, UUID> {
}

