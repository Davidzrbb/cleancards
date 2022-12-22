package arch.hex.infra;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.functional.service.fight_services.FightCreatorService;
import arch.hex.domain.functional.service.fight_services.FightFinderByHeroService;
import arch.hex.domain.functional.service.validation.FightValidator;
import arch.hex.domain.ports.client.fight_api.FightFinderByHeroApi;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import org.springframework.context.annotation.Bean;

public class FightDomainConfig {

    @Bean
    public FightValidator fightValidator(DeckFinderByIdService deckFinderByIdService) {
        return new FightValidator(deckFinderByIdService);
    }
    @Bean
    public FightCreatorService fightCreatorService(FightPersistenceSpi fightPersistenceSpi, IdGenerationService idGenerationService) {
        return new FightCreatorService(fightPersistenceSpi,idGenerationService);
    }

    @Bean
    public FightFinderByHeroApi fightFinderByHero(FightPersistenceSpi fightPersistenceSpi) {
        return new FightFinderByHeroService(fightPersistenceSpi);
    }
}
