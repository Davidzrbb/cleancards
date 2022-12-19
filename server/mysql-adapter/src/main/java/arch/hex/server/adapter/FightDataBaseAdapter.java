package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import arch.hex.server.mapper.FightEntityMapper;
import arch.hex.server.repository.FightRepository;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static arch.hex.server.mapper.FightEntityMapper.fromDomain;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class FightDataBaseAdapter implements FightPersistenceSpi {
    private final FightRepository fightRepository;

    @Override
    @Transactional
    public Either<ApplicationError, Fight> save(Fight fight) {
        val entity = fromDomain(fight);
        return Try(() -> fightRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save fight", null, fight, throwable))
                .map(arch.hex.server.mapper.FightEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Set<Fight>> findByIdHero(String idHero) {
        return Option.of(fightRepository.findFightEntityByIdHeroAllyOrIdHeroEnemy(idHero, idHero)
                .map(FightEntityMapper::toDomain));
    }
}
