package arch.hex.server.entity;

import com.sun.istack.NotNull;
import arch.hex.domain.functional.enums.CardsPackType;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cards_pack")
public class CardsPackEntity {
    @Id
    @EqualsAndHashCode.Include
    String idCardsPack;
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