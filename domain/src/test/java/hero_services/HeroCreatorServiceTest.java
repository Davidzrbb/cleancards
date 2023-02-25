package hero_services;


import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.hero_services.HeroCreatorService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroCreatorServiceTest {

    @InjectMocks private HeroCreatorService heroCreatorService;

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @Mock
    private IdGenerationService idGenerationService;

    @Test
    void testCreate() {
        Hero hero = Hero.builder()
                .name("John")
                .rarity(Rarity.COMMON)
                .speciality(Speciality.MAGE)
                .build();

        Hero savedHero = Hero.builder()
                .idHero("123")
                .name(hero.getName())
                .rarity(hero.getRarity())
                .speciality(hero.getSpeciality())
                .level(1)
                .xp(0)
                .hp(700)
                .power(150)
                .armor(10)
                .build();

        when(idGenerationService.generateNewId()).thenReturn("123");
        when(heroPersistenceSpi.save(any(Hero.class))).thenReturn(Either.right(savedHero));

        Either<ApplicationError, Hero> result = heroCreatorService.create(hero);

        assertEquals(Either.right(savedHero), result);
    }
}