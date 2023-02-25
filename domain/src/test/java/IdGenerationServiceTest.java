import arch.hex.domain.functional.service.IdGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdGenerationServiceTest {
    @InjectMocks
    private IdGenerationService service;

    @Test
    void generateNewId_should_return_a_valid_uuid() {
        String result = service.generateNewId();

        UUID.fromString(result);

        assertEquals(36, result.length());
    }
}