package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import arch.hex.server.mapper.CardsPackEntityMapper;
import arch.hex.server.repository.CardsPackRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.vavr.API.Try;
import static arch.hex.server.mapper.CardsPackEntityMapper.fromDomain;

@Service
@RequiredArgsConstructor
public class CardsPackDataBaseAdapter implements CardsPackPersistenceSpi {
    private final CardsPackRepository cardsPackRepository;


    @Override
    @Transactional
    public Either<ApplicationError, CardsPack> save(CardsPack o) {
        val entity = fromDomain(o);
        return Try(() -> cardsPackRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save cards pack", null, o, throwable))
                .map(CardsPackEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Option<CardsPack> findById(UUID uuid) {
        return cardsPackRepository.findDrivingLicenceEntityById(uuid).map(CardsPackEntityMapper::toDomain);
    }
}
