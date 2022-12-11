package arch.hex.domain.ports.client.cardspack_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import io.vavr.collection.Set;
import io.vavr.control.Either;

import java.util.UUID;

public interface CardsPackOpeningApi {
    Either<ApplicationError, Set<Deck>> open(UUID idCardsPack, UUID idPlayer);
}
