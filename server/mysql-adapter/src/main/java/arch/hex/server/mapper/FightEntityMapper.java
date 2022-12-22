package arch.hex.server.mapper;

import arch.hex.domain.functional.model.Fight;
import arch.hex.server.entity.FightEntity;

public interface FightEntityMapper {
    static Fight toDomain(FightEntity entity) {
        return Fight.builder()
                .idFight(entity.getIdFight())
                .idHeroAlly(HeroEntityMapper.toDomain(entity.getHeroAlly()))
                .idHeroEnemy(HeroEntityMapper.toDomain(entity.getHeroEnemy()))
                .allyWin(entity.isAllyWin())
                .build();
    }

    static FightEntity fromDomain(Fight domain) {
        return FightEntity.builder()
                .idFight(domain.getIdFight())
                .heroAlly(HeroEntityMapper.fromDomain(domain.getIdHeroAlly()))
                .heroEnemy(HeroEntityMapper.fromDomain(domain.getIdHeroEnemy()))
                .allyWin(domain.isAllyWin())
                .build();
    }
}
