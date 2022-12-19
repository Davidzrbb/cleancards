package arch.hex.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Fight {
    @Default
    String idFight = UUID.randomUUID().toString();
    Hero idHeroAlly;
    Hero idHeroEnemy;
    boolean allyWin;
}
