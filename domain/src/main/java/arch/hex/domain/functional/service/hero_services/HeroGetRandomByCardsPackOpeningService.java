package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.client.hero_api.HeroGetRandomByCardsPackOpeningApi;
import io.vavr.collection.Set;

import java.util.ArrayList;

public class HeroGetRandomByCardsPackOpeningService implements HeroGetRandomByCardsPackOpeningApi {
    public Hero getRandomHeroFromListHero(ArrayList<Hero> randomHeroesList) {
        int randomNumber = (int) (Math.random() * randomHeroesList.size());
        int i = 0;
        for (Hero hero : randomHeroesList) {
            if (i == randomNumber)
                return hero;
            i++;
        }
        return randomHeroesList.get(0);
    }
}
