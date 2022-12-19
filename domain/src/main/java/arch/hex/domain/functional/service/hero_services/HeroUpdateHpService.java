package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.functional.model.Hero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroUpdateHpService {

    public Hero getHeroWinnerOfFight(Hero heroAlly, Hero heroEnemy) {
        while (heroAlly.getHp() > 0 && heroEnemy.getHp() > 0) {
            heroAlly = heroAlly.withHp(heroAlly.getHp() - (heroEnemy.getPower() - heroAlly.getArmor()));
            heroEnemy = heroEnemy.withHp(heroEnemy.getHp() - (heroAlly.getPower() - heroEnemy.getArmor()));
        }
        return heroAlly.getHp() > 0 ? heroAlly : heroEnemy;
    }

}
