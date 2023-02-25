package player_services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerUpdateTokenServiceTest {

    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;

    private PlayerUpdateTokenService playerUpdateTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerUpdateTokenService = new PlayerUpdateTokenService(playerPersistenceSpi);
    }

    @Test
    void givenValidPlayerAndToken_whenUpdateToken_thenSuccess() {
        // Given
        Player player = Player.builder().tokens(5).build();
        when(playerPersistenceSpi.save(player.withTokens(10))).thenReturn(Either.right(player.withTokens(10)));

        // When
        Either<ApplicationError, Player> result = playerUpdateTokenService.updateToken(player, 10);

        // Then
        verify(playerPersistenceSpi).save(player.withTokens(10));
        assertEquals(Either.right(player.withTokens(10)), result);
    }

    @Test
    void givenInvalidToken_whenUpdateToken_thenFailure() {
        // Given
        Player player = Player.builder().tokens(5).build();
        when(playerPersistenceSpi.save(player.withTokens(0))).thenReturn(Either.left(ApplicationError.INVALID_ARGUMENTS));

        // When
        Either<ApplicationError, Player> result = playerUpdateTokenService.updateToken(player, 0);

        // Then
        verify(playerPersistenceSpi).save(player.withTokens(0));
        assertEquals(Either.left(ApplicationError), result);
    }
}