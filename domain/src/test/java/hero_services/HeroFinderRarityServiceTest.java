package hero_services;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroFinderRarityService;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Option;
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
class HeroFinderRarityServiceTest {

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @InjectMocks private HeroFinderRarityService heroFinderRarityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        heroFinderRarityService = new HeroFinderRarityService(heroPersistenceSpi);
    }

    @Test
    public void findByRarity_shouldReturnHeroWithMatchingRarity_whenHeroWithMatchingRarityExists() {
        Rarity rarity = Rarity.COMMON;
        Hero expectedHero = Hero.builder()
                .idHero(String.valueOf(1L))
                .name("TestHero")
                .rarity(rarity)
                .speciality(Speciality.TANK)
                .level(1)
                .xp(0)
                .hp(1000)
                .power(100)
                .armor(20)
                .build();
        when(heroPersistenceSpi.findByRarity(rarity)).thenReturn(Option.of(expectedHero));

        Option<Hero> actualHero = heroFinderRarityService.findByRarity(rarity);

        assertEquals(expectedHero, actualHero.get());
    }

    @Test
    public void findByRarity_shouldReturnNone_whenNoHeroWithMatchingRarityExists() {
        Rarity rarity = Rarity.COMMON;
        when(heroPersistenceSpi.findByRarity(rarity)).thenReturn(Option.none());

        Option<Hero> actualHero = heroFinderRarityService.findByRarity(rarity);

        assertEquals(Option.none(), actualHero);
    }
}