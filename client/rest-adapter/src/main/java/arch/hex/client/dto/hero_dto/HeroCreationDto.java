package arch.hex.client.dto.hero_dto;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HeroCreationDto(@JsonProperty("speciality") Speciality speciality,
                              @JsonProperty("rarity") Rarity rarity,
                              @JsonProperty("name") String name) {
}

