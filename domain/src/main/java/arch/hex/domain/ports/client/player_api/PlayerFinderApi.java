package arch.hex.domain.ports.client.player_api;

import arch.hex.domain.functional.model.Player;
import io.vavr.control.Option;

import java.util.UUID;

public interface PlayerFinderApi {
    Option<Player> findById(String idCarsPack);
}
