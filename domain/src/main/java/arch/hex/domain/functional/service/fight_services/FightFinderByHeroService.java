package arch.hex.domain.functional.service.fight_services;

import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.ports.client.fight_api.FightFinderByHeroApi;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FightFinderByHeroService implements FightFinderByHeroApi {
    private final FightPersistenceSpi fightPersistenceSpi;

    public List<Fight> findByIdHero(String idHero) {
        return fightPersistenceSpi.findByIdHero(idHero);
    }
}
