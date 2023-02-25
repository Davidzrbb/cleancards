package arch.hex.domain.functional.service.player_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.deck_services.DeckCreatorService;
import arch.hex.domain.ports.client.player_api.PlayerCreatorApi;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerCreatorService implements PlayerCreatorApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;

    private final IdGenerationService idGenerationService;
    private final DeckCreatorService deckCreatorService;

    @Override
    public Either<ApplicationError, Player> create(String pseudo) {
        Either<ApplicationError, Player> player = playerPersistenceSpi.save(
                Player.builder()
                        .idPlayer(idGenerationService.generateNewId())
                        .pseudo(pseudo)
                        .tokens(4)
                        .build());
        if (player.isRight()) {
            deckCreatorService.create(player.get());
        }
        return player;
    }
}
