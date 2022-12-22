package arch.hex.domain.ports.client.deck_api;

import arch.hex.domain.ApplicationError;
import io.vavr.control.Either;

public interface DeckFightApi {
    Either<ApplicationError, String> fight(String idDeckAlly, String idDeckEnemy);
}
