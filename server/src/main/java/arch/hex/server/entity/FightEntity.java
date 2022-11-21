package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class FightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idFight;

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
