package arch.hex.client.mapper;

import arch.hex.client.dto.hero_dto.HeroDto;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Hero;
import java.util.List;

public interface HeroDtoMapper {

    static HeroDto toDto(Hero hero) {
        return new HeroDto(
                hero.getIdHero(),
                hero.getName(),
                hero.getSpeciality(),
                hero.getRarity(),
                hero.getHp(),
                hero.getXp(),
                hero.getLevel(),
                hero.getPower(),
                hero.getArmor()
        );
    }

    static List<HeroDto> toDto(List<Hero> heroes) {
        return heroes.stream()
                .map(HeroDtoMapper::toDto)
                .toList();
    }

    static Hero heroCreationToDomain(String name, Speciality speciality, Rarity rarity) {
        return Hero.builder().speciality(speciality).rarity(rarity).name(name).build();
    }

}
