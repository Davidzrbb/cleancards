package arch.hex.domain.functional.service.cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckUpdateCardsPackOpeningService;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.functional.service.validation.CardsPackOpeningValidator;
import arch.hex.domain.ports.client.cardspack_api.CardsPackOpeningByIdPlayerAndIdCardsPackApi;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class CardsPackOpeningByIdPlayerAndIdCardsPackService implements CardsPackOpeningByIdPlayerAndIdCardsPackApi {

    private final CardsPackFinderService cardsPackFinderService;
    private final PlayerFinderService playerFinderService;
    private final CardsPackOpeningValidator cardsPackOpeningValidator;
    private final CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService;
    private final DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService;
    private final PlayerUpdateTokenService playerUpdateTokenService;

    //CE SERVICE RETOURNE LES CARTES OBTENUES PAR LE JOUEUR
    @Override
    public Either<ApplicationError, Set<Deck>> getDecksByCardsPackAndPlayer(String idCardsPack, String idPlayer) {
        System.out.println(idCardsPack);
        Option<CardsPack> cardsPack = cardsPackFinderService.findById(idCardsPack);
        Option<Player> player = playerFinderService.findById(idPlayer);
        Validation<ApplicationError, Boolean> validation = cardsPackOpeningValidator.validate(cardsPack, player);
        if (validation.isInvalid()) {
            return Either.left(validation.getError());
        }
        ArrayList<Hero> randomHeroes = cardsPackGetHeroesByDropRateService.getHeroesByDropRate(cardsPack.get());
        Either<ApplicationError, Set<Deck>> decks = getDecksByRandomHeroesAndPlayerAndCardsNumber(randomHeroes, player.get(), cardsPack.get().getCardsNumber());
        if (decks.isRight()) {
            playerUpdateTokenService.updateToken(player.get(), player.get().getTokens() - cardsPack.get().getRequiredTokens());
        }
        return decks;
    }

    public Either<ApplicationError, Set<Deck>> getDecksByRandomHeroesAndPlayerAndCardsNumber(ArrayList<Hero> randomHeroes, Player player, int cardsNumber) {
        Set<Deck> playerDecks = HashSet.empty();
        //about the cards pack type, we add a number of cards into the player deck
        for (int i = 0; i < cardsNumber; i++) {
            Either<ApplicationError, Deck> deck = deckUpdateCardsPackOpeningService.updateByOpeningCardsPack(player, randomHeroes);
            if (deck.isLeft()) {
                return Either.left(deck.getLeft());
            }
            playerDecks = playerDecks.add(deck.get());
        }
        return Either.right(playerDecks);
    }
}
