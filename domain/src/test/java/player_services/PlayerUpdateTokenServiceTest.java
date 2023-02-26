package player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.API.Left;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerUpdateTokenServiceTest {

    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;

    @InjectMocks private PlayerUpdateTokenService playerUpdateTokenService;

    @Test
    void given_valid_player_and_token_when_update_token_then_success() {
        Player player = Player.builder().tokens(5).build();
        when(playerPersistenceSpi.save(player.withTokens(10))).thenReturn(Either.right(player.withTokens(10)));

        Either<ApplicationError, Player> result = playerUpdateTokenService.updateToken(player, 10);

        verify(playerPersistenceSpi).save(player.withTokens(10));
        assertEquals(Either.right(player.withTokens(10)), result);
    }

    @Test
    void given_invalid_token_when_update_token_then_failure() {
        Player player = Player.builder().tokens(5).build();
        val error = new ApplicationError(null, null, null, null);
        when(playerPersistenceSpi.save(player.withTokens(0))).thenReturn(Left(error));

        Either<ApplicationError, Player> result = playerUpdateTokenService.updateToken(player, 0);

        verify(playerPersistenceSpi).save(player.withTokens(0));
        assertThat(result).containsLeftSame(error);
    }
}