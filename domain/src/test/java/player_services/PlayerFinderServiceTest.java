package player_services;

import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerFinderServiceTest {

    @InjectMocks private PlayerFinderService playerFinderService;

    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;

    @Test
    void testFindById() {
        String idPlayer = "1";
        Player player = Player.builder()
                .idPlayer(String.valueOf(1))
                .pseudo("Test Pseudo")
                .tokens(4)
                .build();
        Option<Player> expected = Option.of(player);

        when(playerPersistenceSpi.findById(anyString())).thenReturn(expected);

        Option<Player> actual = playerFinderService.findById(idPlayer);

        assertEquals(expected, actual);
    }
}