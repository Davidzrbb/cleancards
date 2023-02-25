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
@Table(name = "deck_entity")
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