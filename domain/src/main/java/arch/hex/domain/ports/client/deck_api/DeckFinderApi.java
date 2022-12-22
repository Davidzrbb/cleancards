package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.functional.model.Deck;

import io.vavr.control.Option;

import java.util.List;

public interface DeckFinderApi {
    Option<List<Deck>> findByIdPlayer(String player);
}
