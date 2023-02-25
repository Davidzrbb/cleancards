package cards_pack_services;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackGetHeroesByDropRateService;
import arch.hex.domain.functional.service.hero_services.HeroFinderRarityService;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CardsPackGetHeroesByDropRateServiceTest {

    @Mock
    private HeroFinderRarityService heroFinderRarityService;

    @InjectMocks
    private CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService;

    @Test
    void testGetHeroesByDropRate() {
        CardsPack cardsPack = CardsPack.builder()
                .legendaryDropRate(0.1)
                .rareDropRate(0.3)
                .commonDropRate(0.6)
                .build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(Hero.builder().name("Hero 1").rarity(Rarity.LEGENDARY).build());
        heroes.add(Hero.builder().name("Hero 2").rarity(Rarity.RARE).build());
        heroes.add(Hero.builder().name("Hero 3").rarity(Rarity.COMMON).build());
        Mockito.when(heroFinderRarityService.findByRarity(any())).thenReturn(Option.of(heroes.get(0)), Option.of(heroes.get(1)), Option.of(heroes.get(2)));

        ArrayList<Hero> result = cardsPackGetHeroesByDropRateService.getHeroesByDropRate(cardsPack);

        assertEquals(1, countHeroesWithRarity(result, Rarity.LEGENDARY));
        assertEquals(3, countHeroesWithRarity(result, Rarity.RARE));
        assertEquals(6, countHeroesWithRarity(result, Rarity.COMMON));
    }

    private int countHeroesWithRarity(ArrayList<Hero> heroes, Rarity rarity) {
        int count = 0;
        for (Hero hero : heroes) {
            if (hero.getRarity() == rarity) {
                count++;
            }
        }
        return count;
    }
}



