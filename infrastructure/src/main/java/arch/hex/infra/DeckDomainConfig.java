package arch.hex.infra;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.deck_services.*;
import arch.hex.domain.functional.service.hero_services.HeroGetRandomByCardsPackOpeningService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateExperienceService;
import arch.hex.domain.functional.service.hero_services.HeroUpdateHpService;
import arch.hex.domain.functional.service.player_services.PlayerUpdateWinnerService;
import arch.hex.domain.functional.service.validation.FightValidator;
import arch.hex.domain.ports.client.deck_api.DeckFightApi;
import arch.hex.domain.ports.server.model_persistence.DeckPersistenceSpi;
import arch.hex.server.adapter.DeckDataBaseAdapter;
import arch.hex.server.repository.DeckRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


public class DeckDomainConfig {
    @Bean
    @Primary
    public DeckPersistenceSpi deckDatabase(DeckRepository deckRepository) {
        return new DeckDataBaseAdapter(deckRepository);
    }
    @Bean
    public DeckFinderByPlayerService deckFinderByPlayerService(DeckPersistenceSpi deckPersistenceSpi) {
        return new DeckFinderByPlayerService(deckPersistenceSpi);
    }
    @Bean
    public DeckFightApi deckFightService(FightValidator fightValidator,
                                         HeroUpdateHpService heroUpdateHpService,
                                         HeroUpdateExperienceService heroUpdateExperienceService,
                                         PlayerUpdateWinnerService playerUpdateWinnerService) {
        return new DeckFightService(fightValidator, heroUpdateHpService, heroUpdateExperienceService, playerUpdateWinnerService);
    }
    @Bean
    public DeckUpdateCardsPackOpeningService deckUpdateCardsPackOpeningService(DeckFinderByPlayerService deckFinderByPlayerService, DeckPersistenceSpi deckPersistenceSpi, HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpeningService) {
        return new DeckUpdateCardsPackOpeningService(deckFinderByPlayerService, deckPersistenceSpi, heroGetRandomByCardsPackOpeningService);
    }
    @Bean
    public DeckFinderByIdService deckFinderByIdService(DeckPersistenceSpi deckPersistenceSpi) {
        return new DeckFinderByIdService(deckPersistenceSpi);
    }
    @Bean
    public DeckCreatorService deckCreatorService(DeckPersistenceSpi deckPersistenceSpi, IdGenerationService idGenerationService) {
        return new DeckCreatorService(deckPersistenceSpi,idGenerationService);
    }
}
