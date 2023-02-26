package hero_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.API.Right;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroUpdateExperienceServiceTest {

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @InjectMocks
    private HeroUpdateExperienceService heroUpdateExperienceService;

    @Test
    void should_update_experience_for_winning_fight() {
        Hero hero = Hero.builder().xp(1).build();

        when(heroPersistenceSpi.save(any(Hero.class))).thenReturn(Right(hero));

        Either<ApplicationError, Hero> result = heroUpdateExperienceService.updateExperienceForWinningFight(hero);

        assertEquals(hero, result.get());
        assertEquals(1, result.get().getXp());
    }

    @Test
    void should_update_experience_for_winning_fight_and_level_up() {
        Hero hero = Hero.builder().xp(4).level(0).hp(10).armor(10).power(10).build();

        when(heroPersistenceSpi.save(any(Hero.class))).thenReturn(Right(hero.withXp(0).withLevel(1).withHp(11).withArmor(11).withPower(11)));

        Either<ApplicationError, Hero> result = heroUpdateExperienceService.updateExperienceForWinningFight(hero);

        assertEquals(hero.withXp(0).withLevel(1).withHp(11).withArmor(11).withPower(11), result.get());
        assertEquals(0, result.get().getXp());
        assertEquals(1, result.get().getLevel());
        assertEquals(11, result.get().getHp());
        assertEquals(11, result.get().getArmor());
        assertEquals(11, result.get().getPower());
    }
}