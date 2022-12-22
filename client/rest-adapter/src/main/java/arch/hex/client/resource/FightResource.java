package arch.hex.client.resource;

import arch.hex.client.mapper.FightDtoMapper;
import arch.hex.domain.ports.client.fight_api.FightFinderByHeroApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fight")
public class FightResource {
    private final FightFinderByHeroApi fightFinderByHero;

    @GetMapping("/hero/{idHero}")
    public ResponseEntity<Object> findByIdPlayer(@PathVariable String idHero) {
        return fightFinderByHero
                .findByIdHero(idHero)
                .map(FightDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::build, ResponseEntity::ok);
    }
}
