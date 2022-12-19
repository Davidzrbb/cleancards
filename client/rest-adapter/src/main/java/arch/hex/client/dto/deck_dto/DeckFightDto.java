package arch.hex.client.dto.deck_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeckFightDto(@JsonProperty("idDeckAlly") String idDeckAlly,
                           @JsonProperty("idDeckEnemy") String idDeckEnemy) {
}

