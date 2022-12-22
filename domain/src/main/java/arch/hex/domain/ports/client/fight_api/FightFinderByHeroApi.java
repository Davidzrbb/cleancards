package arch.hex.domain.ports.client.fight_api;

import arch.hex.domain.functional.model.Fight;
import io.vavr.control.Option;

import java.util.List;

public interface FightFinderByHeroApi {
    Option<List<Fight>> findByIdHero(String idHero);
}
