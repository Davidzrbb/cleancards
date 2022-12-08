package arch.hex.domain.functional.service;

import java.util.UUID;

public class IdGenerationService {
    public UUID generateNewId() {
        return UUID.randomUUID();
    }
}

