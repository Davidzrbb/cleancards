package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.ports.server.DeckPersistenceSpi;
import arch.hex.server.repository.DeckRepository;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static arch.hex.server.mapper.DeckEntityMapper.fromDomain;
import static io.vavr.API.Try;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
@RequiredArgsConstructor
public class DeckDataBaseAdapter implements DeckPersistenceSpi {
    private final DeckRepository deckRepository;

    @Override
    @Transactional
    public Either<ApplicationError, Deck> save(Deck deck) {
        val entity = fromDomain(deck);
        return Try(() -> deckRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save deck", null, deck, throwable))
                .map(arch.hex.server.mapper.DeckEntityMapper::toDomain);
    }

    @Override
    public Option<Set<Deck>> findAll() {
        return Option.of(deckRepository.findAll())
                .map(deckEntities -> deckEntities
                        .stream()
                        .map(arch.hex.server.mapper.DeckEntityMapper::toDomain)
                        .collect(io.vavr.collection.HashSet.collector()));
    }
}
