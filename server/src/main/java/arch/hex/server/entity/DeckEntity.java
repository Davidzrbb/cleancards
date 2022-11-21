package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class DeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idDeck;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    @NotNull
    Player player;

    @ManyToOne
    @JoinColumn(name = "idHero")
    HeroEntity hero;
}