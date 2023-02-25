package deck_services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;

import arch.hex.domain.functional.service.deck_services.DeckCreatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.IdGenerationService;

import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Either;

@ExtendWith(MockitoExtension.class)
class DeckCreatorServiceTest {

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @Mock
    private IdGenerationService idGenerationService;

    @InjectMocks
    private DeckCreatorService deckCreatorService;

    @Test
    void should_create_deck() {
        String expectedDeckId = UUID.randomUUID().toString();
        when(idGenerationService.generateNewId()).thenReturn(expectedDeckId);

        Player player = Player.builder()
                .pseudo("TestPlayer")
                .tokens(4)
                .winCount(0)
                .build();

        Deck expectedDeck = Deck.builder()
                .idDeck(expectedDeckId)
                .player(player)
                .build();
        when(deckPersistenceSpi.save(expectedDeck)).thenReturn(Either.right(expectedDeck));

        Either<ApplicationError, Deck> result = deckCreatorService.create(player);
        assertEquals(expectedDeck, result.get());
    }
}