package arch.hex.server.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idPlayer;

    @NotNull
    private String pseudo;

    private Integer tokens;

    @Column(columnDefinition = "Integer default 0")
    private Integer winCount = 0;

}
