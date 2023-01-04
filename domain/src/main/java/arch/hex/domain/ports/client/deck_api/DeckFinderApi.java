package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.functional.model.Deck;

import java.util.List;

public interface DeckFinderApi {
    List<Deck> findByIdPlayer(String player);
}
