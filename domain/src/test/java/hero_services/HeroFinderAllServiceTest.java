package hero_services;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroFinderAllService;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroFinderAllServiceTest {

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @InjectMocks private HeroFinderAllService heroFinderAllService;

    @Test
    void shouldReturnEmptyListWhenNoHeroesPersisted() {
        when(heroPersistenceSpi.findAll()).thenReturn(new ArrayList<>());

        List<Hero> heroes = heroFinderAllService.findAll();

        assertEquals(0, heroes.size());
    }

    @Test
    void shouldReturnListOfHeroesWhenHeroesPersisted() {

        List<Hero> heroes = new ArrayList<>();
        Hero hero1 = Hero.builder()
                .idHero("1")
                .name("Hero 1")
                .build();
        Hero hero2 = Hero.builder()
                .idHero("2")
                .name("Hero 2")
                .build();
        heroes.add(hero1);
        heroes.add(hero2);
        when(heroPersistenceSpi.findAll()).thenReturn(heroes);

        List<Hero> persistedHeroes = heroFinderAllService.findAll();

        assertEquals(2, persistedHeroes.size());
        assertEquals(hero1.getIdHero(), persistedHeroes.get(0).getIdHero());
        assertEquals(hero1.getName(), persistedHeroes.get(0).getName());
        assertEquals(hero2.getIdHero(), persistedHeroes.get(1).getIdHero());
        assertEquals(hero2.getName(), persistedHeroes.get(1).getName());
    }
}