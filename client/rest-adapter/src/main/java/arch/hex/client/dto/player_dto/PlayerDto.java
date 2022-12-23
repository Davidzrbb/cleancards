package arch.hex.client.dto.player_dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record PlayerDto(String idPlayer,
                        String pseudo,
                        Integer tokens,
                        Integer winCount) {

}

