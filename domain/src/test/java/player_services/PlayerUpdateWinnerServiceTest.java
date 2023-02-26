package player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class PlayerUpdateWinnerServiceTest {

    @Mock
    PlayerPersistenceSpi playerPersistenceSpi;

    @InjectMocks
    PlayerUpdateWinnerService playerUpdateWinnerService;

    @Test
    void should_update_player_win_count_if_less_than_5_wins() {
        Player player = Player.builder().winCount(4).build();
        when(playerPersistenceSpi.save(player.withWinCount(5))).thenReturn(Either.right(player.withWinCount(5)));

        Either<ApplicationError, Player> result = playerUpdateWinnerService.updatePlayerWinner(player);

        assertEquals(player.withWinCount(5), result.get());
    }

    @Test
    void should_update_player_tokens_if_win_count_is_5() {
        Player player = Player.builder().winCount(5).tokens(0).build();

        when(playerPersistenceSpi.save(player.withWinCount(0).withTokens(1))).thenReturn(Either.right(player.withWinCount(0).withTokens(1)));

        Either<ApplicationError, Player> result = playerUpdateWinnerService.updatePlayerWinner(player);

        assertEquals(player.withWinCount(0).withTokens(1), result.get());
    }
}
