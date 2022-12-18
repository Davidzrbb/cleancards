package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import arch.hex.domain.ports.client.deck_api.DeckUpdateCardsPackOpeningApi;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class DeckUpdateCardsPackOpeningService implements DeckUpdateCardsPackOpeningApi {

    private final DeckFinderService deckFinderService;
    private final DeckPersistenceSpi deckPersistenceSpi;

    private final HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpening;

    public Either<ApplicationError, Deck> updateByOpeningCardsPack(Player player, ArrayList<Hero> heroesRandomList) {
        Option<Set<Deck>> deck = deckFinderService.findByIdPlayer(player.getIdPlayer());
        if (deck.get().isEmpty()) {
            return Either.left(new ApplicationError("No deck found for player", null, player, null));
        }
        //When we register a new player we create an empty line we want to know if we add or if we update this one
        Deck deckToSetHero = deck.get().get();
        if (deckToSetHero.getHero() != null) {
            Deck newDeck = Deck.builder()
                    .idDeck(UUID.randomUUID().toString())
                    .hero(heroGetRandomByCardsPackOpening.getRandomHeroFromListHero(heroesRandomList))
                    .player(player)
                    .build();
            return deckPersistenceSpi.save(newDeck);
        } else {
            deckToSetHero = deckToSetHero.withHero(heroGetRandomByCardsPackOpening.getRandomHeroFromListHero(heroesRandomList));
            return deckPersistenceSpi.save(deckToSetHero);
        }
    }
}
