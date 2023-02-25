package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.*;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Data
@Table(name = "hero_entity")
public class HeroEntity {
    @Id
    @EqualsAndHashCode.Include
    private String idHero;
    @NotNull
    private String name;
    @NotNull
    private Integer hp;
    @NotNull
    private Integer xp;
    @NotNull
    private Integer power;
    @NotNull
    private Integer armor;
    @NotNull
    private String speciality;
    @NotNull
    private String rarity;
    @NotNull
    private Integer level;
}

