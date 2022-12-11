package arch.hex.server.repository;

import arch.hex.domain.functional.model.Player;
import arch.hex.server.entity.DeckEntity;
import arch.hex.server.entity.PlayerEntity;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface DeckRepository extends CrudRepository<DeckEntity, String> {
    Set<DeckEntity> findByPlayer_IdPlayer(String player);
}
