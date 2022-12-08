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
public class DeckEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID idDeck;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    @NotNull
    Player player;

    @ManyToOne
    @JoinColumn(name = "idHero")
    HeroEntity hero;
}