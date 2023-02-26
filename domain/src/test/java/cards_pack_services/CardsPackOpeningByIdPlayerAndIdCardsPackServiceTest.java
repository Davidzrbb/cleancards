package cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackFinderService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackGetHeroesByDropRateService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackOpeningByIdPlayerAndIdCardsPackService;
import arch.hex.domain.functional.service.deck_services.DeckUpdateCardsPackOpeningService;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.functional.service.validation.CardsPackOpeningValidator;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardsPackOpeningByIdPlayerAndIdCardsPackServiceTest {

    @Mock
    private CardsPackFinderService cardsPackFinderService;

    @Mock
    private PlayerFinderService playerFinderService;

    @Mock
    private CardsPackOpeningValidator cardsPackOpeningValidator;

    @Mock
    private CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService;

    @Mock
    private DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService;

    @Mock
    private PlayerUpdateTokenService playerUpdateTokenService;

    @InjectMocks
    private CardsPackOpeningByIdPlayerAndIdCardsPackService cardsPackOpeningService;

    @Test
    void should_return_error_when_cards_pack_not_found() {

        val idCardsPack = UUID.randomUUID().toString();
        val idPlayer = UUID.randomUUID().toString();
        Player player = Player.builder().idPlayer(idPlayer).build();
        when(cardsPackFinderService.findById(any())).thenReturn(Option.none());
        when(playerFinderService.findById(any())).thenReturn(Option.of(player));
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.valid(true));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assertEquals(true, result.isLeft());
        assertEquals("CardsPack not found", result.getLeft().context());
    }

    @Test
    void should_return_error_when_player_not_found() {
        val idCardsPack = UUID.randomUUID().toString();
        val idPlayer = UUID.randomUUID().toString();
        CardsPack cardsPack = CardsPack.builder().idCardsPack(idCardsPack).build();

        when(cardsPackFinderService.findById(any())).thenReturn(Option.of(cardsPack));
        when(playerFinderService.findById(any())).thenReturn(Option.none());
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.valid(true));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assertEquals(true, result.isLeft());
        assertEquals("Player not found", result.getLeft().context());
    }

    @Test
    void should_return_error_when_validation_fails() {
        val idCardsPack = UUID.randomUUID().toString();
        val idPlayer = UUID.randomUUID().toString();
        Player player = Player.builder().idPlayer(idPlayer).build();
        CardsPack cardsPack = CardsPack.builder().idCardsPack(idCardsPack).build();

        when(cardsPackFinderService.findById(any())).thenReturn(Option.of(cardsPack));
        when(playerFinderService.findById(any())).thenReturn(Option.of(player));
        ApplicationError error = new ApplicationError(null, null,null , null);
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.invalid(error));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assertEquals(true, result.isLeft());
    }

    @Test
    void should_return_decks_when_cards_pack_and_player_found() {
        val idCardsPack = UUID.randomUUID().toString();
        val idPlayer = UUID.randomUUID().toString();
        Player player = Player.builder().idPlayer(idPlayer).tokens(2).build();
        CardsPack cardsPack = CardsPack.builder().cardsPackType(CardsPackType.SILVER).requiredTokens(1).idCardsPack(idCardsPack).cardsNumber(10).build();
        Deck deck = Deck.builder().idDeck(UUID.randomUUID().toString()).build();
        Hero hero = Hero.builder().idHero(UUID.randomUUID().toString()).build();

        when(cardsPackFinderService.findById(any())).thenReturn(Option.of(cardsPack));
        when(playerFinderService.findById(any())).thenReturn(Option.of(player));
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.valid(true));
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        heroes.add(hero);
        when(cardsPackGetHeroesByDropRateService.getHeroesByDropRate(any())).thenReturn(heroes);
        when(deckUpdateCardsPackOpeningService.updateByOpeningCardsPack(player, heroes)).thenReturn(Either.right(deck.withHero(hero)));
        when(playerUpdateTokenService.updateToken(player, 1)).thenReturn(Either.right(player.withTokens(1)));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assertEquals(true, result.isRight());
        assertEquals(10, result.get().size());
        assertEquals(hero, result.get().get(5).getHero());
    }
}