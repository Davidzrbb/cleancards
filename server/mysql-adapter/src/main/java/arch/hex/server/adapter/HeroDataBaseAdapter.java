package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import arch.hex.server.mapper.HeroEntityMapper;
import arch.hex.server.repository.HeroRepository;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static arch.hex.server.mapper.HeroEntityMapper.fromDomain;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class HeroDataBaseAdapter implements HeroPersistenceSpi {
    private final HeroRepository heroRepository;

    @Override
    public Either<ApplicationError, Hero> save(Hero hero) {
        val entity = fromDomain(hero);
        return Try(() -> heroRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save cards pack", null, hero, throwable))
                .map(HeroEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Set<Hero>> findAll() {
        return Option.of(heroRepository.findAll())
                .map(heroEntities -> heroEntities
                        .stream()
                        .map(HeroEntityMapper::toDomain)
                        .collect(HashSet.collector()));
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Hero> findByRarity(Rarity rarity) {
        return heroRepository.findHeroEntityByRarity(rarity).map(HeroEntityMapper::toDomain);
    }
}
