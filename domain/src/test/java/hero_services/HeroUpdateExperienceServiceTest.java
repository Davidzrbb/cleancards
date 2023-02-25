package hero_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroUpdateExperienceServiceTest {

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @InjectMocks
    private HeroUpdateExperienceService heroUpdateExperienceService;

   /* @Test
    public void testUpdateExperienceForWinningFight() {
        // Ajouter builder ou mock de hero
        Hero hero = new Hero("John", "Doe", 1, 0, 1, 1, 1);
        when(heroPersistenceSpi.save(hero)).thenReturn(Either.right(hero));

        Either<ApplicationError, Hero> result = heroUpdateExperienceService.updateExperienceForWinningFight(hero);

        assertEquals(hero, result.get());
        assertEquals(1, hero.getXp());
    }*/
}