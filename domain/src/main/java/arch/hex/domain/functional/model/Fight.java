package arch.hex.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Fight {
    @Default
    UUID idFight = UUID.randomUUID();
    Hero idHeroAlly;
    Hero idHeroEnemy;
    boolean isWin;
}
