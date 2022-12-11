package arch.hex.server.entity;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import com.sun.istack.NotNull;
import lombok.*;

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
public class DeckEntity {
    @Id
    @EqualsAndHashCode.Include
    private String idDeck;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    @NotNull
    PlayerEntity player;

    @ManyToOne
    @JoinColumn(name = "idHero")
    HeroEntity hero;
}