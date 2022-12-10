package arch.hex.client.dto.player_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerCreationDto(@JsonProperty("pseudo") String pseudo) {
}
