package arch.hex.domain.ports.client.fight_api;

import arch.hex.domain.functional.model.Fight;

import java.util.List;

public interface FightFinderByHeroApi {
    List<Fight> findByIdHero(String idHero);
}
