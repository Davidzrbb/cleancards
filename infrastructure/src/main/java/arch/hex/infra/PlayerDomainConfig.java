package arch.hex.infra;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.deck_services.DeckCreatorService;
import arch.hex.domain.functional.service.player_services.PlayerCreatorService;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.ports.client.player_api.PlayerCreatorApi;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import arch.hex.server.adapter.PlayerDataBaseAdapter;
import arch.hex.server.repository.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class PlayerDomainConfig {
    @Bean
    @Primary
    public PlayerPersistenceSpi playerDatabase(PlayerRepository playerRepository) {
        return new PlayerDataBaseAdapter(playerRepository);
    }

    @Bean
    public PlayerCreatorApi playerCreatorService(PlayerPersistenceSpi spi, IdGenerationService idGenerationService, DeckCreatorService deckCreatorService) {
        return new PlayerCreatorService(spi, idGenerationService, deckCreatorService);
    }

    @Bean
    public PlayerFinderService playerFinderService(PlayerPersistenceSpi spi) {
        return new PlayerFinderService(spi);
    }

    @Bean
    public PlayerUpdateTokenService playerUpdateTokenService(PlayerPersistenceSpi spi) {
        return new PlayerUpdateTokenService(spi);
    }

    @Bean
    public PlayerUpdateWinnerService playerUpdateWinnerService(PlayerPersistenceSpi spi) {
        return new PlayerUpdateWinnerService(spi);
    }
}
