package arch.hex.client.mapper;

import arch.hex.client.dto.player_dto.PlayerDto;
import arch.hex.domain.functional.model.Player;

public interface PlayerDtoMapper {

    static PlayerDto toDto(Player player) {
        return new PlayerDto(player.getIdPlayer(), player.getPseudo(), player.getTokens(), player.getWinCount());
    }

    static String creationToDomain(String pseudo) {
        return Player.builder().pseudo(pseudo).build().getPseudo();
    }
}
