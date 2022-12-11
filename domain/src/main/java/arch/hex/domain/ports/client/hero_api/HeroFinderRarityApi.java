package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import io.vavr.control.Option;

public interface HeroFinderRarityApi {
    Option<Hero> findByRarity(Rarity rarity);
}
