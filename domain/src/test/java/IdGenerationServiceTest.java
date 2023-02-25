import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.UUID;

import arch.hex.domain.functional.service.IdGenerationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class IdGenerationServiceTest {

    @Mock
    private IdGenerationService.UUIDGenerator uuidGenerator;

    @InjectMocks IdGenerationService idGenerationService;

    @Test
    void should_generate_new_id() {
        UUID expectedUUID = UUID.randomUUID();

        when(uuidGenerator.generateUUID()).thenReturn(expectedUUID);

        String result = idGenerationService.generateNewId();

        assertThat(result).isEqualTo(expectedUUID.toString());
    }
}