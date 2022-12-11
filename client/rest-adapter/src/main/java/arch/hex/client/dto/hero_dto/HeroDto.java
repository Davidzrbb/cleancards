package arch.hex.client.dto.hero_dto;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto(String idHero,
                      String name,
                      Speciality speciality,
                      Rarity rarity,
                      Integer hp,
                      Integer xp,
                      Integer power,
                      Integer armor,
                      Integer level) {
}



