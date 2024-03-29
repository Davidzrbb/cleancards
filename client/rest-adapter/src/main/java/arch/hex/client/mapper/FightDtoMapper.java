package arch.hex.client.mapper;

import arch.hex.client.dto.fight_dto.FightDto;
import arch.hex.domain.functional.model.Fight;

import java.util.List;

public interface FightDtoMapper {

    static FightDto toDto(Fight fight) {
        return new FightDto(
                fight.getIdFight(),
                fight.getHeroAlly(),
                fight.getHeroEnemy(),
                fight.isAllyWin()
        );
    }

    static List<FightDto> toDto(List<Fight> fights) {
        return fights.stream()
                .map(FightDtoMapper::toDto)
                .toList();
    }
}
