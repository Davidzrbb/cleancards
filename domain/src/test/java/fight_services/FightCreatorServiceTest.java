package fight_services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.fight_services.FightCreatorService;
import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FightCreatorServiceTest {

    @Mock
    private FightPersistenceSpi fightPersistenceSpi;

    @Mock
    private IdGenerationService idGenerationService;

    @InjectMocks
    private FightCreatorService fightCreatorService;

    @Test
    void should_create_fight() {
        // add builder hero ou mock
        Fight fight = Fight.builder().build();
        String newId = UUID.randomUUID().toString();
        when(idGenerationService.generateNewId()).thenReturn(newId);
        when(fightPersistenceSpi.save(any(Fight.class)))
                .thenReturn(Either.right(fight));

        Either<ApplicationError, Fight> result = fightCreatorService.create(fight);
        assertTrue(result.isRight());
        assertEquals(fight, result.get());
    }
}