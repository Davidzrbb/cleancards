package arch.hex.server.entity;

import com.sun.istack.NotNull;
import arch.hex.domain.functional.enums.CardsPackType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cards-pack")
public class CardsPackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID idCardsPack;
    @NotNull
    private Integer requiredTokens;
    @NotNull
    private Integer cardsNumber;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CardsPackType type;
    @NotNull
    private Double legendaryDropRate;
    @NotNull
    private Double rareDropRate;
    @NotNull
    private Double commonDropRate;

}