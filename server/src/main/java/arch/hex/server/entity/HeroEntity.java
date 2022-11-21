package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class HeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idHero;
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
    private Speciality specialty;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Rarity rarity;
    @NotNull
    private Integer level;
}

