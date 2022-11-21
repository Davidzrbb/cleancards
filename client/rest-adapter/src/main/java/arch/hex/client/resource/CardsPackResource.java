package arch.hex.client.resource;

import arch.hex.client.mapper.CardsPackDtoMapper;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.ports.client.CardsPackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static arch.hex.client.mapper.CardsPackDtoMapper.cardsPackCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cards-pack")
public class CardsPackResource {
    private final CardsPackApi cardsPackApi;

    @PostMapping
    public ResponseEntity<Object> createCardsPack(@RequestBody CardsPackType cardsPackType) {
        return cardsPackApi
                .cardsPackCreatorService(cardsPackCreationToDomain(cardsPackType))
                .map(CardsPackDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
