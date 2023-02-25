package deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFinderByPlayerService;
import arch.hex.domain.functional.service.deck_services.DeckUpdateCardsPackOpeningService;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeckUpdateCardsPackOpeningServiceTest {
    @Mock
    private DeckFinderByPlayerService deckFinderByPlayerService;

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @Mock
    private HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpeningService;

    @InjectMocks
    private DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService;

//    @Test
//    void test_update_by_opening_cards_pack_when_no_deck_found() {
//        Player player = Player.builder().idPlayer(UUID.randomUUID().toString()).build();
//        ArrayList<Hero> heroesRandomList = new ArrayList<>();
//        when(deckFinderByPlayerService.findByIdPlayer(player.getIdPlayer())).thenReturn(List.of());
//
//        Either<ApplicationError, Deck> result = deckUpdateCardsPackOpeningService.updateByOpeningCardsPack(player, heroesRandomList);
//
//        assertEquals(ApplicationError.class, result.getLeft().getClass());
//        assertEquals("No deck found for player", result.getLeft().context());
//        assertEquals(player, result.getRight().player());
//        verifyNoInteractions(deckPersistenceSpi, heroGetRandomByCardsPackOpeningService);
//    }

    @Test
    void test_update_by_opening_cards_pack_when_deck_has_hero() {
        Player player = Player.builder().idPlayer(UUID.randomUUID().toString()).build();
        ArrayList<Hero> heroesRandomList = new ArrayList<>();
        Hero hero = Hero.builder().idHero(UUID.randomUUID().toString()).build();
        Deck deck = Deck.builder().idDeck(UUID.randomUUID().toString()).player(player).hero(hero).build();

        when(deckFinderByPlayerService.findByIdPlayer(player.getIdPlayer())).thenReturn(List.of(deck));
        when(heroGetRandomByCardsPackOpeningService.getRandomHeroFromListHero(heroesRandomList)).thenReturn(hero);
        when(deckPersistenceSpi.save(any())).thenReturn(Either.right(deck));

        Either<ApplicationError, Deck> result = deckUpdateCardsPackOpeningService.updateByOpeningCardsPack(player, heroesRandomList);

        assertEquals(Either.class, result.getClass());
        assertEquals(Deck.class, result.get().getClass());
        verify(deckPersistenceSpi, times(1)).save(any());
    }

    @Test
    void test_update_by_opening_cards_pack_when_deck_has_no_hero() {
        Player player = Player.builder().idPlayer(UUID.randomUUID().toString()).build();
        ArrayList<Hero> heroesRandomList = new ArrayList<>();
        Hero hero = Hero.builder().idHero(UUID.randomUUID().toString()).build();
        Deck deck = Deck.builder().idDeck(UUID.randomUUID().toString()).player(player).hero(null).build();

        when(deckFinderByPlayerService.findByIdPlayer(player.getIdPlayer())).thenReturn(List.of(deck));
        when(heroGetRandomByCardsPackOpeningService.getRandomHeroFromListHero(heroesRandomList)).thenReturn(hero);
        when(deckPersistenceSpi.save(any())).thenReturn(Either.right(deck));

        Either<ApplicationError, Deck> result = deckUpdateCardsPackOpeningService.updateByOpeningCardsPack(player, heroesRandomList);

        assertEquals(Either.class, result.getClass());
        assertEquals(Deck.class, result.get().getClass());
        verify(deckPersistenceSpi, times(1)).save(any());
    }
}