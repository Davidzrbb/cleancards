package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.client.hero_api.HeroFinderApi;
import arch.hex.domain.ports.server.HeroPersistenceSpi;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderService implements HeroFinderApi {

    private final HeroPersistenceSpi heroPersistenceSpi;

    @Override
    public Option<Set<Hero>> findAll() {
        return heroPersistenceSpi.findAll();
    }
}

