package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.ports.client.deck_api.DeckFinderApi;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DeckFinderByPlayerService implements DeckFinderApi {

    private final DeckPersistenceSpi deckPersistenceSpi;

    @Override
    public List<Deck> findByIdPlayer(String idPlayer) {
        return deckPersistenceSpi.findByIdPlayer(idPlayer);
    }
}


