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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardsPackGetHeroesByDropRateServiceTest {

    @Mock
    private HeroFinderRarityService heroFinderRarityService;

    @InjectMocks
    private CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService;

    @Test
    void should_get_heroes_by_drop_rate_silver() {
        CardsPack cardsPack = CardsPack.builder()
                .legendaryDropRate(0.05)
                .rareDropRate(0.2)
                .commonDropRate(0.75)
                .build();

        Hero heroLegendary = Hero.builder().rarity(Rarity.LEGENDARY).build();
        Hero heroRare = Hero.builder().rarity(Rarity.RARE).build();
        Hero heroCommon = Hero.builder().rarity(Rarity.COMMON).build();
        when(heroFinderRarityService.findByRarity(Rarity.LEGENDARY)).thenReturn(Option.of(heroLegendary));
        when(heroFinderRarityService.findByRarity(Rarity.RARE)).thenReturn(Option.of(heroRare));
        when(heroFinderRarityService.findByRarity(Rarity.COMMON)).thenReturn(Option.of(heroCommon));

        ArrayList<Hero> result = cardsPackGetHeroesByDropRateService.getHeroesByDropRate(cardsPack);

        assertEquals(5, count_heroes_with_rarity(result, Rarity.LEGENDARY));
        assertEquals(20, count_heroes_with_rarity(result, Rarity.RARE));
        assertEquals(75, count_heroes_with_rarity(result, Rarity.COMMON));
    }

    @Test
    void should_get_heroes_by_drop_rate_diamond() {
        CardsPack cardsPack = CardsPack.builder()
                .legendaryDropRate(0.15)
                .rareDropRate(0.35)
                .commonDropRate(0.5)
                .build();

        Hero heroLegendary = Hero.builder().rarity(Rarity.LEGENDARY).build();
        Hero heroRare = Hero.builder().rarity(Rarity.RARE).build();
        Hero heroCommon = Hero.builder().rarity(Rarity.COMMON).build();
        when(heroFinderRarityService.findByRarity(Rarity.LEGENDARY)).thenReturn(Option.of(heroLegendary));
        when(heroFinderRarityService.findByRarity(Rarity.RARE)).thenReturn(Option.of(heroRare));
        when(heroFinderRarityService.findByRarity(Rarity.COMMON)).thenReturn(Option.of(heroCommon));

        ArrayList<Hero> result = cardsPackGetHeroesByDropRateService.getHeroesByDropRate(cardsPack);

        assertEquals(15, count_heroes_with_rarity(result, Rarity.LEGENDARY));
        assertEquals(35, count_heroes_with_rarity(result, Rarity.RARE));
        assertEquals(50, count_heroes_with_rarity(result, Rarity.COMMON));
    }

    private int count_heroes_with_rarity(ArrayList<Hero> heroes, Rarity rarity) {
        int count = 0;
        for (Hero hero : heroes) {
            if (hero.getRarity() == rarity) {
                count++;
            }
        }
        return count;
    }
}



