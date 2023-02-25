package hero_services;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroGetRandomByCardsPackOpeningServiceTest {

    @Mock
    private ArrayList<Hero> mockHeroesList;

    @InjectMocks private HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpeningService;

    /*@Test
    public void testGetRandomHeroFromListHero() {

        // Ajouter un builder ou mock de hero
        Hero hero1 = new Hero("1", "Hero 1", Rarity.COMMON, null, 1, 0, 100, 50, 10);
        Hero hero2 = new Hero("2", "Hero 2", Rarity.RARE, null, 1, 0, 150, 75, 15);
        Hero hero3 = new Hero("3", "Hero 3", Rarity.LEGENDARY, null, 1, 0, 200, 100, 20);

        mockHeroesList.add(hero1);
        mockHeroesList.add(hero2);
        mockHeroesList.add(hero3);

        when(mockHeroesList.size()).thenReturn(3);
        when(mockHeroesList.get(0)).thenReturn(hero1);
        when(mockHeroesList.get(1)).thenReturn(hero2);
        when(mockHeroesList.get(2)).thenReturn(hero3);
        when(Math.random()).thenReturn(0.5);

        Hero result = heroGetRandomByCardsPackOpeningService.getRandomHeroFromListHero(mockHeroesList);

        assertEquals(hero2, result);
    }*/
}