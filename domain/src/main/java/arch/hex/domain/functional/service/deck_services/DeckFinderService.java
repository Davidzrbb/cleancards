package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.client.deck_api.DeckFinderApi;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeckFinderService implements DeckFinderApi {

    private final DeckPersistenceSpi deckPersistenceSpi;

    @Override
    public Option<Set<Deck>> findByPlayer(Player player) {
        return deckPersistenceSpi.findByPlayer(player);
    }
}


