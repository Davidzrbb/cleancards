package arch.hex.domain.ports.client.cardspack_api;

import arch.hex.domain.functional.model.CardsPack;
import io.vavr.control.Option;

import java.util.UUID;

public interface CardsPackFinderApi {
    Option<CardsPack> findById(String idCarsPack);
}
