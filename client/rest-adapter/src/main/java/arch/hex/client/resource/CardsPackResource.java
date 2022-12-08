package arch.hex.client.resource;

import arch.hex.client.dto.cards_pack_dto.CardsPackCreationDto;
import arch.hex.client.mapper.CardsPackDtoMapper;
import arch.hex.domain.ports.client.CardsPackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static arch.hex.client.mapper.CardsPackDtoMapper.cardsPackCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cards-pack")
public class CardsPackResource {
    private final CardsPackApi cardsPackApi;

    @PostMapping
    public ResponseEntity<Object> createCardsPack(@RequestBody CardsPackCreationDto dto) {
        return cardsPackApi
                .create(cardsPackCreationToDomain(dto.cardsPackType()))
                .map(CardsPackDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
