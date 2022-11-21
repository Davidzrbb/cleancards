package arch.hex.domain.functional.service.cards_pack_service;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.service.validation.CardsPackTypeValidator;
import arch.hex.domain.ports.client.CardsPackApi;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateCardsPackService implements CardsPackApi {

    private final CardsPackPersistenceSpi spi;

    @Override
    public Either<ApplicationError, CardsPack> cardsPackCreatorService(CardsPackType cardsPackType) {
        Validation<ApplicationError, CardsPackType> validation = CardsPackTypeValidator.validate(cardsPackType);
        if (validation.isValid()) {
            if (validation.get().equals(CardsPackType.Argent)) {
                return spi.save(CardsPack.builder()
                        .cardsPackType(CardsPackType.Argent)
                        .cardsNumber(3)
                        .legendaryDropRate(0.05)
                        .rareDropRate(0.2)
                        .commonDropRate(0.75)
                        .requiredTokens(1)
                        .build());
            } else {
                return spi.save(CardsPack.builder()
                        .cardsPackType(CardsPackType.Diamant)
                        .cardsNumber(5)
                        .legendaryDropRate(0.15)
                        .rareDropRate(0.35)
                        .commonDropRate(0.5)
                        .requiredTokens(2)
                        .build());
            }
        } else {
            return Either.left(validation.getError());
        }
    }
}
