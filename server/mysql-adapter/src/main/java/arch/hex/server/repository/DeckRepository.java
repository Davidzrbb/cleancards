package arch.hex.server.repository;

import arch.hex.server.entity.DeckEntity;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface DeckRepository extends CrudRepository<DeckEntity, String> {
    List<DeckEntity> findByPlayer_IdPlayer(String player);

    Option<DeckEntity> findDeckEntityByIdDeck(String idDeck);
}
