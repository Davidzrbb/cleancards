package arch.hex.domain.ports.client.cardspack_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import io.vavr.collection.Set;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public interface CardsPackOpeningByIdPlayerAndIdCardsPackApi {
    Either<ApplicationError, List<Deck>> getDecksByCardsPackAndPlayer(String cardsPack, String player);
    
}
