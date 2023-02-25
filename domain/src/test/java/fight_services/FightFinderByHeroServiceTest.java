package fight_services;

import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.fight_services.FightFinderByHeroService;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
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
class FightFinderByHeroServiceTest {

    @Mock
    private FightPersistenceSpi fightPersistenceSpi;

    @InjectMocks
    private FightFinderByHeroService fightFinderByHeroService;

    @Test
    void should_find_by_id_hero() {
        String idHero = "hero123";
        Hero heroAlly = Hero.builder().idHero("hero123").name("hero456").build();
        Hero heroEnemy = Hero.builder().idHero("hero789").name("hero789").build();
        Fight fight1 = Fight.builder().idFight("fight1").heroAlly(heroAlly).heroEnemy(heroEnemy).build();
        Fight fight2 = Fight.builder().idFight("fight2").heroAlly(heroAlly).heroEnemy(heroEnemy).build();
        List<Fight> expectedFights = new ArrayList<>();
        expectedFights.add(fight1);
        expectedFights.add(fight2);
        when(fightPersistenceSpi.findByIdHero(idHero)).thenReturn(expectedFights);

        List<Fight> actualFights = fightFinderByHeroService.findByIdHero(idHero);

        assertEquals(expectedFights, actualFights);
    }
}