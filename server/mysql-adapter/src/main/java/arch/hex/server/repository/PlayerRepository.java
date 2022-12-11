package arch.hex.server.repository;

import arch.hex.server.entity.PlayerEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
    Option<PlayerEntity> findPlayerEntityByIdPlayer(String idPlayer);
}
