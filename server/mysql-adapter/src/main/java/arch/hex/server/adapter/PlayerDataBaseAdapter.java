package arch.hex.server.adapter;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import arch.hex.server.mapper.PlayerEntityMapper;
import arch.hex.server.repository.PlayerRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    @Transactional(readOnly = true)
    public Option<Player> findById(String idPlayer) {
        return playerRepository.findPlayerEntityByIdPlayer(idPlayer)
                .map(PlayerEntityMapper::toDomain);
    }


}

