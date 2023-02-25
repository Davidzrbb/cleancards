package hero_services;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroUpdateHpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroUpdateHpServiceTest {

    @Mock
    private Hero heroAlly;

    @Mock
    private Hero heroEnemy;

    @InjectMocks
    private HeroUpdateHpService heroUpdateHpService;

    @Test
    public void testGetHeroWinnerOfFight() {
        // builder hero ou mock
        when(heroAlly.getHp()).thenReturn(10);
        when(heroEnemy.getHp()).thenReturn(10);
        Hero expectedWinner = heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy);
        assertEquals(heroAlly, expectedWinner);
    }
}