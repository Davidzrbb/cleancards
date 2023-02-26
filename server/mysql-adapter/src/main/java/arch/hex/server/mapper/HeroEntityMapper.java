package arch.hex.server.mapper;

import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Hero;
import arch.hex.server.entity.HeroEntity;

public interface HeroEntityMapper {
    static Hero toDomain(HeroEntity entity) {
        return Hero.builder()
                .idHero(entity.getIdHero())
                .name(entity.getName())
                .level(entity.getLevel())
                .xp(entity.getXp())
                .rarity(Rarity.valueOf(entity.getRarity()))
                .speciality(Speciality.valueOf(entity.getSpeciality()))
                .hp(entity.getHp())
                .power(entity.getPower())
                .armor(entity.getArmor())
                .build();
    }

    static HeroEntity fromDomain(Hero domain) {
        return HeroEntity.builder()
                .idHero(domain.getIdHero())
                .name(domain.getName())
                .level(domain.getLevel())
                .xp(domain.getXp())
                .rarity(domain.getRarity().name())
                .speciality(domain.getSpeciality().name())
                .hp(domain.getHp())
                .power(domain.getPower())
                .armor(domain.getArmor())
                .build();
    }
}
