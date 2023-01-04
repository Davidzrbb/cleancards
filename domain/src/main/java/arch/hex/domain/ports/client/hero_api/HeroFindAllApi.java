package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.functional.model.Hero;

import java.util.List;

public interface HeroFindAllApi {
    List<Hero> findAll();

}
