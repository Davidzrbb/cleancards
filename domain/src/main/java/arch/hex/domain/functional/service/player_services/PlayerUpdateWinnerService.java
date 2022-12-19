package arch.hex.domain.functional.service.player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerUpdateWinnerService {
    private final PlayerPersistenceSpi playerPersistenceSpi;

    public Either<ApplicationError, Player> updatePlayerWinner(Player player) {
        if (player.getWinCount() < 5) {
            player = player.withWinCount(player.getWinCount() + 1);
        } else {
            player = player.withTokens(player.getTokens() + 1);
            player = player.withWinCount(0);
        }
        return playerPersistenceSpi.save(player);
    }
}
