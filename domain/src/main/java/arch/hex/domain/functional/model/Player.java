package arch.hex.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Player {
    @Default
    UUID idPlayer = UUID.randomUUID();
    String pseudo;

    @Default
    @With
    Integer tokens = 4;

    @Default
    @With
    Integer winCount = 0;
}
