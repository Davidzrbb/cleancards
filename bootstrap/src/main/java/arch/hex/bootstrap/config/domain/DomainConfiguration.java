package arch.hex.bootstrap.config.domain;

import arch.hex.domain.functional.service.cards_pack_service.CreateCardsPackService;
import arch.hex.domain.ports.client.CardsPackApi;
import arch.hex.domain.ports.server.CardsPackPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DomainConfiguration {

    @Bean

    public CardsPackApi cardsPackCreatorService(CardsPackPersistenceSpi spi) {
        return new CreateCardsPackService(spi);
    }

}
