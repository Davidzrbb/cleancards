package arch.hex.client.resource;

import arch.hex.client.dto.hero_dto.HeroCreationDto;
import arch.hex.client.mapper.HeroDtoMapper;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.ports.client.hero_api.HeroCreatorApi;
import arch.hex.domain.ports.client.hero_api.HeroFinderApi;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static arch.hex.client.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {
    private final HeroCreatorApi heroCreatorApi;
    private final HeroFinderApi heroFinderApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationDto dto) {
        return heroCreatorApi
                .create(heroCreationToDomain(dto.name(), dto.speciality(), dto.rarity()))
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return heroFinderApi
                .findAll()
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.noContent()::build, ResponseEntity::ok);
    }
}
