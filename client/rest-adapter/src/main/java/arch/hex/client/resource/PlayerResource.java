package arch.hex.client.resource;

import arch.hex.client.dto.player_dto.PlayerCreationDto;
import arch.hex.client.mapper.PlayerDtoMapper;
import arch.hex.domain.ports.client.player_api.PlayerCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static arch.hex.client.mapper.PlayerDtoMapper.creationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class PlayerResource {

    private final PlayerCreatorApi playerCreatorApi;

    @PostMapping
    public ResponseEntity<Object> createPlayer(@RequestBody PlayerCreationDto dto) {
        return playerCreatorApi
                .create(creationToDomain(dto.pseudo()))
                .map(PlayerDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

}
