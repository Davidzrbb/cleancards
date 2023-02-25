package deck_services;

import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckFinderByIdServiceTest {

    @InjectMocks  private DeckFinderByIdService deckFinderByIdService;

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @Test
    void should_find_by_id_when_deck_exist() {
        String idDeck = "deckId";
        Player player = Player.builder().pseudo("player1").build();
        Hero hero = Hero.builder().name("player1").build();
        Deck expectedDeck = Deck.builder()
                .idDeck(idDeck)
                .player(player)
                .hero(hero)
                .build();
        when(deckPersistenceSpi.findById(idDeck)).thenReturn(Option.of(expectedDeck));

        Option<Deck> result = deckFinderByIdService.findById(idDeck);

        assertEquals(expectedDeck, result.get());
    }

    @Test
    void should_return_none_when_deck_not_exist() {
        String idDeck = "nonExistentDeckId";
        when(deckPersistenceSpi.findById(idDeck)).thenReturn(Option.none());

        Option<Deck> result = deckFinderByIdService.findById(idDeck);

        assertEquals(Option.none(), result);
    }
}