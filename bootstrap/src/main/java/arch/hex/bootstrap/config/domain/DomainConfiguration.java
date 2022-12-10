package arch.hex.bootstrap.config.domain;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackCreatorService;
import arch.hex.domain.functional.service.deck_services.DeckCreatorService;
import arch.hex.domain.functional.service.hero_services.HeroCreatorCreatorService;
import arch.hex.domain.functional.service.hero_services.HeroFinderService;
import arch.hex.domain.functional.service.player_services.PlayerCreatorService;
import arch.hex.domain.ports.client.CardsPackApi;
import arch.hex.domain.ports.client.PlayerApi;
import arch.hex.domain.ports.client.hero_api.HeroCreatorApi;
import arch.hex.domain.ports.client.hero_api.HeroFinderApi;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import arch.hex.domain.ports.server.HeroPersistenceSpi;
import arch.hex.domain.ports.server.PlayerPersistenceSpi;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "arch.hex.server.repository")
@EntityScan(basePackages = "arch.hex.server.entity")
@ComponentScan(basePackages = {"arch.hex.domain"})
@Import({IdGenerationService.class, DeckCreatorService.class})
public class DomainConfiguration {

    @Bean
    public CardsPackApi cardsPackCreatorService(CardsPackPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new CardsPackCreatorService(spi, idGenerationService);
    }

    @Bean
    public HeroCreatorApi heroCreatorService(HeroPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new HeroCreatorCreatorService(spi, idGenerationService);
    }

    @Bean
    public HeroFinderApi heroFinderService(HeroPersistenceSpi spi) {
        return new HeroFinderService(spi);
    }

    @Bean
    public PlayerApi playerCreatorService(PlayerPersistenceSpi spi, IdGenerationService idGenerationService, DeckCreatorService deckCreatorService) {
        return new PlayerCreatorService(spi, idGenerationService, deckCreatorService);
    }

}
