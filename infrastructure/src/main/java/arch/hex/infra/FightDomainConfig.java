package arch.hex.infra;

import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import arch.hex.domain.functional.service.validation.FightValidator;
import org.springframework.context.annotation.Bean;

public class FightDomainConfig {

    @Bean
    public FightValidator fightValidator(DeckFinderByIdService deckFinderByIdService) {
        return new FightValidator(deckFinderByIdService);
    }
}
