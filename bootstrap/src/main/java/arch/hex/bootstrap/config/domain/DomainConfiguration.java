package arch.hex.bootstrap.config.domain;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackCreatorService;
import arch.hex.domain.functional.service.hero_services.HeroCreatorService;
import arch.hex.domain.ports.client.CardsPackApi;
import arch.hex.domain.ports.client.HeroApi;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import arch.hex.domain.ports.server.HeroPersistenceSpi;
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
@Import({IdGenerationService.class})
public class DomainConfiguration {

    @Bean
    public CardsPackApi cardsPackCreatorService(CardsPackPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new CardsPackCreatorService(spi, idGenerationService);
    }

    @Bean
    public HeroApi heroCreatorService(HeroPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new HeroCreatorService(spi, idGenerationService);
    }

}
