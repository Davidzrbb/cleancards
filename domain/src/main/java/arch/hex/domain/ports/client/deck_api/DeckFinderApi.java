package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface DeckFinderApi {
    Option<Set<Deck>> findByPlayer(Player player);
}
