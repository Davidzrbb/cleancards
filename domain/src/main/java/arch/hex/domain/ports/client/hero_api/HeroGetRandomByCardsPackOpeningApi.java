package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.functional.model.Hero;
import io.vavr.collection.Set;

import java.util.ArrayList;

public interface HeroGetRandomByCardsPackOpeningApi {
    Hero getRandomHeroFromListHero(ArrayList<Hero> randomHeroesList);
}
