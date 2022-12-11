package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.client.hero_api.HeroFinderRarityApi;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderRarityService implements HeroFinderRarityApi {
    private final HeroPersistenceSpi heroPersistenceSpi;

    public Option<Hero> findByRarity(Rarity rarity) {
        return heroPersistenceSpi.findByRarity(rarity);
    }
}
