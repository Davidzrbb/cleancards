package arch.hex.domain.functional.model;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Setter;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
@Setter
public class Hero {
    @Default
    String idHero = UUID.randomUUID().toString();
    String name;
    @Default
    @With
    Speciality speciality = Speciality.MAGE;
    @Default
    @With
    Rarity rarity = Rarity.COMMON;
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
