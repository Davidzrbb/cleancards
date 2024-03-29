package arch.hex.domain.functional.model;

import arch.hex.domain.functional.enums.CardsPackType;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CardsPack {
    @Default
    String idCardsPack = UUID.randomUUID().toString();
    Integer requiredTokens;
    Integer cardsNumber;
    CardsPackType cardsPackType;
    Double legendaryDropRate;
    Double rareDropRate;
    Double commonDropRate;
}
