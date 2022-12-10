package arch.hex.client.dto.player_dto;

import java.util.UUID;

public record PlayerDto(UUID idPlayer,
                        String pseudo,
                        Integer tokens,
                        Integer winCount) {

}

