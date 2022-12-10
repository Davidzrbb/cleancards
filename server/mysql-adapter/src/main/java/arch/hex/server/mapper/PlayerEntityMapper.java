package arch.hex.server.mapper;

import arch.hex.domain.functional.model.Player;
import arch.hex.server.entity.PlayerEntity;

public interface PlayerEntityMapper {

    static Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .idPlayer(entity.getIdPlayer())
                .pseudo(entity.getPseudo())
                .tokens(entity.getTokens())
                .winCount(entity.getWinCount())
                .build();
    }

    static PlayerEntity fromDomain(Player domain) {
        return PlayerEntity.builder()
                .idPlayer(domain.getIdPlayer())
                .pseudo(domain.getPseudo())
                .tokens(domain.getTokens())
                .winCount(domain.getWinCount())
                .build();
    }
}
