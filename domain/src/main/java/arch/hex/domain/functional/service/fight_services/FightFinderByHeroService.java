package arch.hex.domain.functional.service.fight_services;

import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FightFinderByHeroService {
    private final FightPersistenceSpi fightPersistenceSpi;

    public Option<Set<Fight>> findByIdHero(String idHero) {
        return fightPersistenceSpi.findByIdHero(idHero);
    }
}
