package arch.hex.domain.functional.service.cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.ports.client.CardsPackApi;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CardsPackCreatorService implements CardsPackApi {

    private final CardsPackPersistenceSpi cardsPackPersistenceSpi;
    private final IdGenerationService idGenerationService;


    @Override
    public Either<ApplicationError, CardsPack> create(CardsPackType cardsPackType) {
        if (cardsPackType.equals(CardsPackType.Argent)) {
            return cardsPackPersistenceSpi.save(CardsPack.builder()
                    .idCardsPack(idGenerationService.generateNewId())
                    .cardsPackType(CardsPackType.Argent)
                    .cardsNumber(3)
                    .legendaryDropRate(0.05)
                    .rareDropRate(0.2)
                    .commonDropRate(0.75)
                    .requiredTokens(1)
                    .build());
        }
        return cardsPackPersistenceSpi.save(CardsPack.builder()
                .idCardsPack(idGenerationService.generateNewId())
                .cardsPackType(CardsPackType.Diamond)
                .cardsNumber(5)
                .legendaryDropRate(0.15)
                .rareDropRate(0.35)
                .commonDropRate(0.5)
                .requiredTokens(2)
                .build());

    }
}
