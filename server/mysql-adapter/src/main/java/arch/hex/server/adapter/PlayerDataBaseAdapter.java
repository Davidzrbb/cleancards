package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.server.PlayerPersistenceSpi;
import arch.hex.server.mapper.PlayerEntityMapper;
import arch.hex.server.repository.PlayerRepository;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import static arch.hex.server.mapper.PlayerEntityMapper.fromDomain;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class PlayerDataBaseAdapter implements PlayerPersistenceSpi {
    private final PlayerRepository playerRepository;

    @Override
    public Either<ApplicationError, Player> save(Player player) {
        val entity = fromDomain(player);
        return Try(() -> playerRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save player", null, player, throwable))
                .map(PlayerEntityMapper::toDomain);
    }

    @Override
    public Option<Set<Player>> findAll() {
        return Option.of(playerRepository.findAll())
                .map(playerEntities -> playerEntities
                        .stream()
                        .map(PlayerEntityMapper::toDomain)
                        .collect(HashSet.collector()));
    }
}

