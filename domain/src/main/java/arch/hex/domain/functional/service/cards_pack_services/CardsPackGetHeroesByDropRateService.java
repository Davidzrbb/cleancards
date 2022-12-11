package arch.hex.domain.functional.service.cards_pack_services;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.service.hero_services.HeroFinderRarityService;
import arch.hex.domain.ports.client.cardspack_api.CardsPackGetHeroesByDropRateApi;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class CardsPackGetHeroesByDropRateService implements CardsPackGetHeroesByDropRateApi {

    private final HeroFinderRarityService heroFinderRarityService;

    @Override
    public ArrayList<Hero> getHeroesByDropRate(CardsPack cardsPack) {
        ArrayList<Hero> randomHeroesList = new ArrayList<>();
        int dropRateLegendary = (int) ((cardsPack.getLegendaryDropRate() * 100));
        int dropRateRare = (int) ((cardsPack.getRareDropRate() * 100));
        int dropRateCommon = (int) ((cardsPack.getCommonDropRate() * 100));

        for (int i = 0; i < dropRateLegendary; i++) {
            randomHeroesList.add(heroFinderRarityService.findByRarity(Rarity.Legendary).getOrElseThrow(() -> new RuntimeException("Hero not found")));
        }
        for (int i = 0; i < dropRateRare; i++) {
            randomHeroesList.add(heroFinderRarityService.findByRarity(Rarity.Rare).getOrElseThrow(() -> new RuntimeException("Hero not found")));
        }
        for (int i = 0; i < dropRateCommon; i++) {
            randomHeroesList.add(heroFinderRarityService.findByRarity(Rarity.Common).getOrElseThrow(() -> new RuntimeException("Hero not found")));
        }
        return randomHeroesList;
    }
}

