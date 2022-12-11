package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import io.vavr.collection.Set;
import io.vavr.control.Either;

import java.util.ArrayList;

public interface DeckUpdateCardsPackOpeningApi {
    Either<ApplicationError, Deck> updateByOpeningCardsPack(Player player, ArrayList<Hero> heroesRandomList);
}
