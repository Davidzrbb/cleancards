package arch.hex.client.dto.player_dto;

import java.util.UUID;

public record PlayerDto(String idPlayer,
                        String pseudo,
                        Integer tokens,
                        Integer winCount) {

}

