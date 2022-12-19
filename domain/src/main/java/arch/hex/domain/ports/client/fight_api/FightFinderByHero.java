package arch.hex.domain.ports.client.fight_api;

import arch.hex.domain.functional.model.Fight;
import io.vavr.control.Option;

public interface FightFinderByHero {
    Option<Fight> findByIdHero(String idHero);
}
