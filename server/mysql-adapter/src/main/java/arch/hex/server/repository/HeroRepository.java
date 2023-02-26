package arch.hex.server.repository;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.server.entity.HeroEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface HeroRepository extends JpaRepository<HeroEntity, String> {
    Option<HeroEntity> findHeroEntityByRarity(String rarity);
}
