package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import arch.hex.server.mapper.DeckEntityMapper;
import arch.hex.server.repository.DeckRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static arch.hex.server.mapper.DeckEntityMapper.fromDomain;
import static io.vavr.API.Try;

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
    @Transactional(readOnly = true)
    public List<Deck> findByIdPlayer(String idPlayer) {
        return deckRepository.findByPlayer_IdPlayer(idPlayer)
                .stream()
                .map(DeckEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Deck> findById(String idDeck) {
        return deckRepository.findDeckEntityByIdDeck(idDeck)
                .map(DeckEntityMapper::toDomain);
    }
}
