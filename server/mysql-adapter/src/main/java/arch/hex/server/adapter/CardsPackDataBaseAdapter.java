package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import arch.hex.server.mapper.CardsPackEntityMapper;
import arch.hex.server.mapper.HeroEntityMapper;
import arch.hex.server.repository.CardsPackRepository;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.Try;
import static arch.hex.server.mapper.CardsPackEntityMapper.fromDomain;

@Service
@RequiredArgsConstructor
public class CardsPackDataBaseAdapter implements CardsPackPersistenceSpi {
    private final CardsPackRepository cardsPackRepository;


    @Override
    @Transactional
    public Either<ApplicationError, CardsPack> save(CardsPack cardsPack) {
        val entity = fromDomain(cardsPack);
        return Try(() -> cardsPackRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save cards pack", null, cardsPack, throwable))
                .map(CardsPackEntityMapper::toDomain);
    }

    @Override
    public Option<Set<CardsPack>> findAll() {
        return Option.of(cardsPackRepository.findAll())
                .map(cardsPackEntities -> cardsPackEntities
                        .stream()
                        .map(CardsPackEntityMapper::toDomain)
                        .collect(HashSet.collector()));
    }
}
