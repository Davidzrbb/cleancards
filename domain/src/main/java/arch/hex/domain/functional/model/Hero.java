package arch.hex.domain.functional.model;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Hero {
    @Default
    UUID idHero = UUID.randomUUID();
    String name;
    Speciality speciality;
    Rarity rarity;
    @With
    Integer hp;
    @With
    Integer xp;
    @With
    Integer power;
    @With
    Integer armor;
    @With
    Integer level;


}
