package cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Deck;
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

import java.util.List;
import java.util.UUID;

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
        CardsPack cardsPack = CardsPack.builder().idCardsPack(idCardsPack).build();
        Player player = Player.builder().idPlayer(idPlayer).build();
        when(cardsPackFinderService.findById(any())).thenReturn(Option.none());
        when(playerFinderService.findById(any())).thenReturn(Option.of(player));
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.valid(true));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assert result.isLeft();
    }

   /* @Test
    public void should_return_error_when_player_not_found() {
        when(cardsPackFinderService.findById(any())).thenReturn(Option.of(cardsPack));
        when(playerFinderService.findById(any())).thenReturn(Option.none());
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.valid(true));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assert result.isLeft();
    }

    @Test
    public void should_return_error_when_validation_fails() {
        when(cardsPackFinderService.findById(any())).thenReturn(Option.of(cardsPack));
        when(playerFinderService.findById(any())).thenReturn(Option.of(player));
        ApplicationError error = new ApplicationError(null, null,null , null);
        when(cardsPackOpeningValidator.validate(any(), any())).thenReturn(Validation.invalid(error));

        Either<ApplicationError, List<Deck>> result = cardsPackOpeningService.getDecksByCardsPackAndPlayer(idCardsPack, idPlayer);

        assert result.isLeft();
    }*/
}