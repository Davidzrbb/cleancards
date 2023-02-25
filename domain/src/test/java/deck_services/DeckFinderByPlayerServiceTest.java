package deck_services;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;

import arch.hex.domain.functional.service.deck_services.DeckFinderByPlayerService;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckFinderByPlayerServiceTest {

    @InjectMocks private DeckFinderByPlayerService deckFinderByPlayerService;

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @Test
    void should_find_by_id_player_when_decks_exist() {
        String expectedPlayerId = UUID.randomUUID().toString();

        Player player = Player.builder().idPlayer(expectedPlayerId).build();
        Deck deck1 = Deck.builder().player(player).build();
        Deck deck2 = Deck.builder().player(player).build();
        List<Deck> expectedDecks = Arrays.asList(deck1, deck2);
        when(deckPersistenceSpi.findByIdPlayer(expectedPlayerId)).thenReturn(expectedDecks);

        List<Deck> result = deckFinderByPlayerService.findByIdPlayer(expectedPlayerId);

        assertEquals(expectedDecks, result);
    }

    @Test
    void should_find_by_id_player_when_no_decks_exist() {
        String idPlayer = "player2";
        List<Deck> expectedDecks = Arrays.asList();
        when(deckPersistenceSpi.findByIdPlayer(idPlayer)).thenReturn(expectedDecks);

        List<Deck> result = deckFinderByPlayerService.findByIdPlayer(idPlayer);

        assertEquals(expectedDecks, result);
    }
}