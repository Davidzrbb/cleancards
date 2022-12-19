package arch.hex.domain.functional.service.hero_services;


import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.server.model_persistence.HeroPersistenceSpi;
import arch.hex.domain.ports.server.persistence_spi.PersistenceHeroSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroUpdateExperienceService {
    private final HeroPersistenceSpi heroPersistenceSpi;

    public Either<ApplicationError, Hero> getExperienceForWinningFight(Hero heroWinner) {
        heroWinner = heroWinner.withXp(heroWinner.getXp() + 1);
        if (heroWinner.getXp() == 5) {
            heroWinner = heroWinner.withXp(0);
            heroWinner = heroWinner.withLevel(heroWinner.getLevel() + 1);
            heroWinner = heroWinner.withHp(heroWinner.getHp() + (heroWinner.getHp() * 10) / 100);
            heroWinner = heroWinner.withPower(heroWinner.getPower() + (heroWinner.getPower() * 10) / 100);
            heroWinner = heroWinner.withArmor(heroWinner.getArmor() + (heroWinner.getArmor() * 10) / 100);
        }
        return heroPersistenceSpi.save(heroWinner);
    }

}
