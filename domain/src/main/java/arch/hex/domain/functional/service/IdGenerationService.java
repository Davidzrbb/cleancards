package arch.hex.domain.functional.service;

import java.util.UUID;

public class IdGenerationService {
    public String generateNewId() {
        return UUID.randomUUID().toString();
    }
}