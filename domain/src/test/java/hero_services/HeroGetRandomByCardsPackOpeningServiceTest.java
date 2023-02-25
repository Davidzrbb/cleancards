package hero_services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HeroGetRandomByCardsPackOpeningServiceTest {

    @InjectMocks
    private HeroGetRandomByCardsPackOpeningService service;

    @ParameterizedTest
    @CsvSource({
            "1,hero1,1",
            "2,hero2,2",
            "3,hero3,3",
            "1,hero1,4",
            "2,hero2,5",
            "3,hero3,6"
    })
    void testGetRandomHeroFromListHero(int id, String name) {
        Hero hero = Hero.builder().idHero(Integer.toString(id)).name(name).build();
        Hero hero2 = Hero.builder().idHero(Integer.toString(id)).name(name).build();
        Hero hero3 = Hero.builder().idHero(Integer.toString(id)).name(name).build();
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(hero2);
        heroes.add(hero3);

        Hero actualHero = service.getRandomHeroFromListHero((ArrayList<Hero>) heroes);

        assertEquals(hero, actualHero);
    }
}