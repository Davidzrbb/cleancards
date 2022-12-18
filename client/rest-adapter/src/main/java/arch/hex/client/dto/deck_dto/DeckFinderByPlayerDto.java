package arch.hex.client.dto.deck_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeckFinderByPlayerDto(@JsonProperty("idPlayer") String idPlayer) {
}

