package arch.hex.client.dto.cards_pack_dto;

import arch.hex.domain.functional.enums.CardsPackType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CardsPackOpeningDto(@JsonProperty("idCardsPack") UUID idCardsPack,
                                  @JsonProperty("idPlayer") UUID idPlayer) {
}

