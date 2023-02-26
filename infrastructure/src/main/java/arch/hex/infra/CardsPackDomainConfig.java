package arch.hex.infra;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackCreatorService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackFinderService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackGetHeroesByDropRateService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackOpeningByIdPlayerAndIdCardsPackService;
import arch.hex.domain.functional.service.deck_services.DeckUpdateCardsPackOpeningService;
import arch.hex.domain.functional.service.hero_services.HeroFinderRarityService;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.functional.service.validation.CardsPackOpeningValidator;
import arch.hex.domain.ports.client.cardspack_api.CardsPackCreatorApi;
import arch.hex.domain.ports.client.cardspack_api.CardsPackOpeningByIdPlayerAndIdCardsPackApi;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import arch.hex.server.adapter.CardsPackDataBaseAdapter;
import arch.hex.server.repository.CardsPackRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CardsPackDomainConfig {

    @Bean
    public IdGenerationService idGenerationService() {
        return new IdGenerationService();
    }

    @Bean
    @Primary
    public CardsPackPersistenceSpi cardsPackDatabase(CardsPackRepository cardsPackRepository) {
        return new CardsPackDataBaseAdapter(cardsPackRepository);
    }

    @Bean
    public CardsPackCreatorApi cardsPackCreatorService(CardsPackPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new CardsPackCreatorService(spi, idGenerationService);
    }

    @Bean
    public CardsPackOpeningByIdPlayerAndIdCardsPackApi cardsPackOpeningByIdPlayerAndIdCardsPackService(CardsPackFinderService cardsPackFinderService, PlayerFinderService playerFinderService, CardsPackOpeningValidator cardsPackOpeningValidator,
                                                                                                       CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService, DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService,
                                                                                                       PlayerUpdateTokenService playerUpdateTokenService) {
        return new CardsPackOpeningByIdPlayerAndIdCardsPackService(cardsPackFinderService, playerFinderService, cardsPackOpeningValidator, cardsPackGetHeroesByDropRateService, deckUpdateCardsPackOpeningService, playerUpdateTokenService);
    }

    @Bean
    public CardsPackFinderService cardsPackFinderService(CardsPackPersistenceSpi spi) {
        return new CardsPackFinderService(spi);
    }

    @Bean
    public CardsPackOpeningValidator cardsPackOpeningValidator() {
        return new CardsPackOpeningValidator();
    }

    @Bean
    public CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService(HeroFinderRarityService heroFinderRarityService) {
        return new CardsPackGetHeroesByDropRateService(heroFinderRarityService);
    }
}