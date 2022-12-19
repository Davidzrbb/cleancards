package arch.hex.server.entity;

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
@Table(name = "player")
public class PlayerEntity {
    @Id
    @EqualsAndHashCode.Include
    private String idPlayer;

    @NotNull
    private String pseudo;

    private Integer tokens;

    @Column(columnDefinition = "Integer default 0")
    private Integer winCount = 0;

}
