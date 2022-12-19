package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeckCreatorService {

    private final DeckPersistenceSpi deckPersistenceSpi;
    private final IdGenerationService idGenerationService;


    public Either<ApplicationError, Deck> save(Player player) {
        return deckPersistenceSpi.save(Deck.builder()
                .idDeck(idGenerationService.generateNewId().toString())
                .player(player)
                .build());
    }
}
