package arch.hex.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import arch.hex.domain.functional.enums.CardsPackType;

public record CardsPackCreationDto(@JsonProperty("cardsPackType") CardsPackType cardsPackType) {
}
