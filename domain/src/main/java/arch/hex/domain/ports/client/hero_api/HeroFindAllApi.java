package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface HeroFindAllApi {
    Option<Set<Hero>> findAll();

}
