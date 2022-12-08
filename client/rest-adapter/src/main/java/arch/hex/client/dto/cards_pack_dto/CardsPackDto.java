package arch.hex.client.dto.cards_pack_dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import arch.hex.domain.functional.enums.CardsPackType;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record CardsPackDto(UUID idCardsPack,
                           Integer requiredTokens,
                           Integer cardsNumber,
                           CardsPackType cardsPackType,
                           Double legendaryDropRate,
                           Double rareDropRate,
                           Double commonDropRate) {

}

