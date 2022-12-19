package arch.hex.bootstrap.config.domain;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackCreatorService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackFinderService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackGetHeroesByDropRateService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackOpeningByIdPlayerAndIdCardsPackService;
import arch.hex.domain.functional.service.deck_services.*;
import arch.hex.domain.functional.service.hero_services.*;
import arch.hex.domain.functional.service.player_services.PlayerCreatorService;
import arch.hex.domain.functional.service.player_services.PlayerFinderService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateTokenService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.functional.service.validation.CardsPackOpeningValidator;
import arch.hex.domain.functional.service.validation.FightValidator;
import arch.hex.domain.ports.client.cardspack_api.CardsPackCreatorApi;
import arch.hex.domain.ports.client.cardspack_api.CardsPackOpeningByIdPlayerAndIdCardsPackApi;
import arch.hex.domain.ports.client.deck_api.DeckFightApi;
import arch.hex.domain.ports.client.deck_api.DeckFinderApi;
import arch.hex.domain.ports.client.player_api.PlayerCreatorApi;
import arch.hex.domain.ports.client.hero_api.HeroCreatorApi;
import arch.hex.domain.ports.client.hero_api.HeroFindAllApi;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import arch.hex.domain.ports.server.model_persistence.PlayerPersistenceSpi;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "arch.hex.server.repository")
@EntityScan(basePackages = "arch.hex.server.entity")
@ComponentScan(basePackages = {"arch.hex.domain"})
@Import({IdGenerationService.class, DeckCreatorService.class, CardsPackFinderService.class,
        PlayerFinderService.class, CardsPackOpeningValidator.class,
        CardsPackGetHeroesByDropRateService.class, DeckUpdateCardsPackOpeningService.class,
        HeroFinderRarityService.class, DeckFinderByPlayerService.class, HeroGetRandomByCardsPackOpeningService.class
        , PlayerUpdateTokenService.class, FightValidator.class, HeroUpdateHpService.class,
        HeroUpdateExperienceService.class, PlayerUpdateWinnerService.class, DeckFinderByIdService.class})
public class DomainConfiguration {


    @Bean
    public CardsPackCreatorApi cardsPackCreatorService(CardsPackPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new CardsPackCreatorService(spi, idGenerationService);
    }

    @Bean
    public HeroCreatorApi heroCreatorService(HeroPersistenceSpi spi, IdGenerationService idGenerationService) {
        return new HeroCreatorService(spi, idGenerationService);
    }

    @Bean
    public HeroFindAllApi heroFinderService(HeroPersistenceSpi spi) {
        return new HeroFinderAllService(spi);
    }

    @Bean
    public PlayerCreatorApi playerCreatorService(PlayerPersistenceSpi spi, IdGenerationService idGenerationService, DeckCreatorService deckCreatorService) {
        return new PlayerCreatorService(spi, idGenerationService, deckCreatorService);
    }

    @Bean
    public CardsPackOpeningByIdPlayerAndIdCardsPackApi cardsPackOpeningByIdPlayerAndIdCardsPackService(CardsPackFinderService cardsPackFinderService, PlayerFinderService playerFinderService, CardsPackOpeningValidator cardsPackOpeningValidator,
                                                                                                       CardsPackGetHeroesByDropRateService cardsPackGetHeroesByDropRateService, DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService,
                                                                                                       PlayerUpdateTokenService playerUpdateTokenService) {
        return new CardsPackOpeningByIdPlayerAndIdCardsPackService(cardsPackFinderService, playerFinderService, cardsPackOpeningValidator, cardsPackGetHeroesByDropRateService, deckUpdateCardsPackOpeningService, playerUpdateTokenService);
    }

    @Bean
    @Primary
    public DeckFinderApi deckFinderService(DeckPersistenceSpi deckPersistenceSpi) {
        return new DeckFinderByPlayerService(deckPersistenceSpi);
    }

    @Bean
    public DeckFightApi deckFightService(FightValidator fightValidator,
                                         HeroUpdateHpService heroUpdateHpService,
                                         HeroUpdateExperienceService heroUpdateExperienceService,
                                         PlayerUpdateWinnerService playerUpdateWinnerService) {
        return new DeckFightService(fightValidator, heroUpdateHpService, heroUpdateExperienceService, playerUpdateWinnerService);
    }
}
