package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeckFinderByIdService {
    private final DeckPersistenceSpi deckPersistenceSpi;

    public Option<Deck> findById(String idDeck) {
        return deckPersistenceSpi.findById(idDeck);
    }
}
