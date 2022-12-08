package arch.hex.client.resource;

import arch.hex.client.dto.cards_pack_dto.CardsPackCreationDto;
import arch.hex.client.dto.hero_dto.HeroCreationDto;
import arch.hex.domain.ports.client.HeroApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static arch.hex.client.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {
    private final HeroApi heroApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationDto dto) {
        return heroApi
                .create(heroCreationToDomain(dto.name(), dto.speciality(), dto.rarity()))
                .map(arch.hex.client.mapper.HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
