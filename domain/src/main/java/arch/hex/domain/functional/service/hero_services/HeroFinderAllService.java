package arch.hex.domain.functional.service.hero_services;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.client.hero_api.HeroFindAllApi;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderAllService implements HeroFindAllApi {

    private final HeroPersistenceSpi heroPersistenceSpi;

    @Override
    public Option<List<Hero>> findAll() {
        return heroPersistenceSpi.findAll();
    }
}

