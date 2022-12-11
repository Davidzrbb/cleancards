package arch.hex.domain.ports.client.cardspack_api;

import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Hero;
import io.vavr.collection.Set;

import java.util.ArrayList;

public interface CardsPackGetHeroesByDropRateApi {
    ArrayList<Hero> getHeroesByDropRate(CardsPack cardsPack);
}

