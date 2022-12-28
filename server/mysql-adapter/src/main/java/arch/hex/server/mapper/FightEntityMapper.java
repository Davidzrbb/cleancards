package arch.hex.server.mapper;

import arch.hex.domain.functional.model.Fight;
import arch.hex.server.entity.FightEntity;

public interface FightEntityMapper {
    static Fight toDomain(FightEntity entity) {
        return Fight.builder()
                .idFight(entity.getIdFight())
                .heroAlly(HeroEntityMapper.toDomain(entity.getHeroAlly()))
                .heroEnemy(HeroEntityMapper.toDomain(entity.getHeroEnemy()))
                .allyWin(entity.isAllyWin())
                .build();
    }

    static FightEntity fromDomain(Fight domain) {
        return FightEntity.builder()
                .idFight(domain.getIdFight())
                .heroAlly(HeroEntityMapper.fromDomain(domain.getHeroAlly()))
                .heroEnemy(HeroEntityMapper.fromDomain(domain.getHeroEnemy()))
                .allyWin(domain.isAllyWin())
                .build();
    }
}
