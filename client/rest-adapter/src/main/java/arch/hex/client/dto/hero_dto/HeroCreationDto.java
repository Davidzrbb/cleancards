package arch.hex.client.dto.hero_dto;

import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Hero;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HeroCreationDto(@JsonProperty("speciality") Speciality speciality,
                              @JsonProperty("rarity") Rarity rarity,
                              @JsonProperty("name") String name) {
}

