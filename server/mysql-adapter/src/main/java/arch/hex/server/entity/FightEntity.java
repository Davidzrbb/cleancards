package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Data
@Table(name = "fight")
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
    private boolean allyWin;


}
