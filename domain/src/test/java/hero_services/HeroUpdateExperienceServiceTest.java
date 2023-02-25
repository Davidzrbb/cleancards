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
}