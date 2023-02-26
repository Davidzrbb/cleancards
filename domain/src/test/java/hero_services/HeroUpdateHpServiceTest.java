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

    @InjectMocks
    private HeroUpdateHpService heroUpdateHpService;

    @Test
    void should_return_winner_of_fight() {
        Hero heroAlly = Hero.builder().hp(10).armor(10).power(20).build();
        Hero heroEnemy = Hero.builder().hp(10).armor(10).power(20).build();
        Hero expectedWinner = heroUpdateHpService.getHeroWinnerOfFight(heroAlly, heroEnemy);
        assertEquals(heroEnemy.withHp(0), expectedWinner);
    }
}