package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.functional.model.Hero;
import io.vavr.control.Option;

import java.util.List;

public interface HeroFindAllApi {
    Option<List<Hero>> findAll();

}
