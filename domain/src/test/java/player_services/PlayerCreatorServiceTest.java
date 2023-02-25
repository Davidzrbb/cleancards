package player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.deck_services.DeckCreatorService;
import arch.hex.domain.functional.service.player_services.PlayerCreatorService;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerCreatorServiceTest {

    private PlayerCreatorService playerCreatorService;

    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;

    @Mock
    private IdGenerationService idGenerationService;

    @Mock
    private DeckCreatorService deckCreatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        playerCreatorService = new PlayerCreatorService(playerPersistenceSpi, idGenerationService, deckCreatorService);
    }

    @Test
    void testCreate() {
        String pseudo = "Test Pseudo";
        Player player = Player.builder()
                .idPlayer(String.valueOf(1))
                .pseudo(pseudo)
                .tokens(4)
                .build();
        Either<ApplicationError, Player> expected = Either.right(player);

        when(idGenerationService.generateNewId()).thenReturn(String.valueOf(1L));
        when(playerPersistenceSpi.save(any(Player.class))).thenReturn(expected);

        Either<ApplicationError, Player> actual = playerCreatorService.create(pseudo);

        assertEquals(expected, actual);
        verify(deckCreatorService).create(player);
    }
}