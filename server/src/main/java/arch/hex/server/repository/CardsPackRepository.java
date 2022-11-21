package arch.hex.server.repository;

import arch.hex.server.entity.CardsPackEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface CardsPackRepository extends JpaRepository<CardsPackEntity, UUID> {
    @EntityGraph(attributePaths = "offences")
    Option<CardsPackEntity> findDrivingLicenceEntityById(UUID id);
}
