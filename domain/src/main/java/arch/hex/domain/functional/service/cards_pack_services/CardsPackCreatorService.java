package arch.hex.domain.functional.service.cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.ports.client.cardspack_api.CardsPackCreatorApi;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CardsPackCreatorService implements CardsPackCreatorApi {

    private final CardsPackPersistenceSpi cardsPackPersistenceSpi;
    private final IdGenerationService idGenerationService;


    @Override
    public Either<ApplicationError, CardsPack> create(CardsPackType cardsPackType) {
        if (cardsPackType.equals(CardsPackType.Silver)) {
            return cardsPackPersistenceSpi.save(CardsPack.builder()
                    .idCardsPack(idGenerationService.generateNewId())
                    .cardsPackType(CardsPackType.Silver)
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
