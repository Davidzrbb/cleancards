package arch.hex.domain.functional.service.deck_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class DeckUpdateCardsPackOpeningService  {

    private final DeckFinderByPlayerService deckFinderByPlayerService;
    private final DeckPersistenceSpi deckPersistenceSpi;
    private final HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpening;

    public Either<ApplicationError, Deck> updateByOpeningCardsPack(Player player, ArrayList<Hero> heroesRandomList) {
        List<Deck> deck = deckFinderByPlayerService.findByIdPlayer(player.getIdPlayer());
        if (deck.isEmpty()) {
            return Either.left(new ApplicationError("No deck found for player", null, player, null));
        }
        Deck deckToSetHero = deck.get(0);
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
