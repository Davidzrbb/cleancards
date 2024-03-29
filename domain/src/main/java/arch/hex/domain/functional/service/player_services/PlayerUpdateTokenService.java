package arch.hex.domain.functional.service.player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerUpdateTokenService {
    private final PlayerPersistenceSpi playerPersistenceSpi;

    public Either<ApplicationError, Player> updateToken(Player player, int token) {
        return playerPersistenceSpi.save(player.withTokens(token));
    }
}
