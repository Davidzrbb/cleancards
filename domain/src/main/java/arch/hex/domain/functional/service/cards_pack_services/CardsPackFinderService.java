package arch.hex.domain.functional.service.cards_pack_services;

import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.ports.client.cardspack_api.CardsPackFinderApi;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CardsPackFinderService implements CardsPackFinderApi {

    private final CardsPackPersistenceSpi cardsPackPersistenceSpi;

    @Override
    public Option<CardsPack> findById(String idCarsPack) {
        return cardsPackPersistenceSpi.findById(idCarsPack);
    }
}
