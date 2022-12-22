package arch.hex.domain.functional.service.fight_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.service.IdGenerationService;

import arch.hex.domain.ports.server.model_persistence.FightPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FightCreatorService {

    private final FightPersistenceSpi fightPersistenceSpi;
    private final IdGenerationService idGenerationService;

    public Either<ApplicationError, Fight> create(Fight fight) {
        return fightPersistenceSpi.save(Fight.builder()
                .idFight(idGenerationService.generateNewId())
                .idHeroEnemy(fight.getIdHeroEnemy())
                .idHeroAlly(fight.getIdHeroAlly())
                .allyWin(fight.isAllyWin())
                .build());
    }
}
