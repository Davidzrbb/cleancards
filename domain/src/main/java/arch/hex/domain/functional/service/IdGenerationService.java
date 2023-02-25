package arch.hex.domain.functional.service;

import java.util.UUID;

public class IdGenerationService {
    private final UUIDGenerator uuidGenerator;

    public IdGenerationService(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    public String generateNewId() {
        return uuidGenerator.generateUUID().toString();
    }

    public interface UUIDGenerator {
        UUID generateUUID();
    }
}