package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class FightEntity {
    @Id
    @EqualsAndHashCode.Include
    private String idFight;

    @ManyToOne
    @JoinColumn(name = "idHeroAlly")
    @NotNull
    HeroEntity idHeroAlly;

    @ManyToOne
    @JoinColumn(name = "idHeroEnemy")
    @NotNull
    HeroEntity idHeroEnemy;

    @NotNull
    private boolean isWin;

}
