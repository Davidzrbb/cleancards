package arch.hex.server.mapper;

import arch.hex.domain.functional.model.Fight;
import arch.hex.server.entity.FightEntity;

public interface FightEntityMapper {
    static Fight toDomain(FightEntity entity) {
        return Fight.builder()
                .idFight(entity.getIdFight())
                .idHeroAlly(HeroEntityMapper.toDomain(entity.getIdHeroAlly()))
                .idHeroEnemy(HeroEntityMapper.toDomain(entity.getIdHeroEnemy()))
                .allyWin(entity.isAllyWin())
                .build();
    }

    static FightEntity fromDomain(Fight domain) {
        return FightEntity.builder()
                .idFight(domain.getIdFight())
                .idHeroAlly(HeroEntityMapper.fromDomain(domain.getIdHeroAlly()))
                .idHeroEnemy(HeroEntityMapper.fromDomain(domain.getIdHeroEnemy()))
                .allyWin(domain.isAllyWin())
                .build();
    }
}
