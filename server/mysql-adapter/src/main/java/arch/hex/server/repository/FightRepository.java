package arch.hex.server.repository;

import arch.hex.server.entity.FightEntity;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface FightRepository extends JpaRepository<FightEntity, String> {
    Set<FightEntity> findFightEntityByIdHeroAllyOrIdHeroEnemy(String idHero, String idHero2);
}
