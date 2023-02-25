package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.ports.client.hero_api.HeroCreatorApi;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi heroPersistenceSpi;

    private final IdGenerationService idGenerationService;

    @Override
    public Either<ApplicationError, Hero> create(Hero hero) {
        int hp = 0;
        int power = 0;
        int armor = 0;
        switch (hero.getSpeciality()) {
            case TANK -> {
                hp = 1000;
                power = 100;
                armor = 20;
            }
            case ASSASSIN -> {
                hp = 800;
                power = 200;
                armor = 5;
            }
            case MAGE -> {
                hp = 700;
                power = 150;
                armor = 10;
            }
        }
        if (hero.getRarity() == Rarity.RARE) {
            hp += (hp * 10) / 100;
            power += (power * 10) / 100;
            armor += (armor * 10) / 100;
        }
        if (hero.getRarity() == Rarity.LEGENDARY){
            hp += (hp * 20) / 100;
            power += (power * 20) / 100;
            armor += (armor * 20) / 100;
        }
        return heroPersistenceSpi.save(Hero.builder()
                .idHero(idGenerationService.generateNewId())
                .name(hero.getName())
                .rarity(hero.getRarity())
                .speciality(hero.getSpeciality())
                .level(1)
                .xp(0)
                .hp(hp)
                .power(power)
                .armor(armor)
                .build());
    }
}


