package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.*;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Data
@Table(name = "hero")
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
    @Enumerated(EnumType.STRING)
    @NotNull
    private Speciality speciality;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Rarity rarity;
    @NotNull
    private Integer level;
}

