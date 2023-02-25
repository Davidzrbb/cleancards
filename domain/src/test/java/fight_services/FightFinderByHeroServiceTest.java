package fight_services;

import arch.hex.domain.functional.model.Fight;
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

    /*@Test
    void should_find_by_id_hero() {
        // add builder fight ou mock
        String idHero = "hero123";
        List<Fight> expectedFights = new ArrayList<>();
        expectedFights.add(new Fight("fight1", "hero456", "hero123"));
        expectedFights.add(new Fight("fight2", "hero789", "hero123"));
        when(fightPersistenceSpi.findByIdHero(idHero)).thenReturn(expectedFights);

        List<Fight> actualFights = fightFinderByHeroService.findByIdHero(idHero);

        assertEquals(expectedFights, actualFights);
    }*/
}