package arch.hex.domain.functional.service.player_services;

import arch.hex.domain.functional.model.Player;
import arch.hex.domain.ports.client.player_api.PlayerFinderApi;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerFinderService implements PlayerFinderApi {

    private final PlayerPersistenceSpi playerPersistenceSpi;

    @Override
    public Option<Player> findById(String idPlayer) {
        return playerPersistenceSpi.findById(idPlayer);
    }
}
