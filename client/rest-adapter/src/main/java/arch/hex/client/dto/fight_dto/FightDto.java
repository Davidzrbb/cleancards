package arch.hex.client.dto.fight_dto;

import arch.hex.domain.functional.model.Hero;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record FightDto(
        String idFight,
        Hero idHeroAlly,
        Hero idHeroEnemy,
        boolean allyWin
) {
}