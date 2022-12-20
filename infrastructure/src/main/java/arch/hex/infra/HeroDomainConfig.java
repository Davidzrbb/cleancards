package arch.hex.infra;

import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.hero_services.*;
import arch.hex.domain.ports.client.hero_api.HeroCreatorApi;
import arch.hex.domain.ports.client.hero_api.HeroFindAllApi;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import arch.hex.server.adapter.HeroDataBaseAdapter;
import arch.hex.server.repository.HeroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class HeroDomainConfig {
    @Bean
    @Primary
    public HeroPersistenceSpi heroDatabase(HeroRepository heroRepository) {
        return new HeroDataBaseAdapter(heroRepository);
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
    public HeroFinderRarityService heroFinderRarityService(HeroPersistenceSpi spi) {
        return new HeroFinderRarityService(spi);
    }

    @Bean
    public HeroGetRandomByCardsPackOpeningService heroGetRandomByCardsPackOpeningService() {
        return new HeroGetRandomByCardsPackOpeningService();
    }

    @Bean
    public HeroUpdateHpService heroUpdateHpService() {
        return new HeroUpdateHpService();
    }

    @Bean
    public HeroUpdateExperienceService heroUpdateExperienceService(HeroPersistenceSpi spi) {
        return new HeroUpdateExperienceService(spi);
    }
}
