package arch.hex.client.resource;

import arch.hex.client.dto.cards_pack_dto.CardsPackCreationDto;
import arch.hex.client.mapper.CardsPackDtoMapper;
import arch.hex.client.mapper.DeckDtoMapper;
import arch.hex.domain.ports.client.cardspack_api.CardsPackCreatorApi;
import arch.hex.domain.ports.client.cardspack_api.CardsPackOpeningByIdPlayerAndIdCardsPackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static arch.hex.client.mapper.CardsPackDtoMapper.cardsPackCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cards-pack")
public class CardsPackResource {
    private final CardsPackCreatorApi cardsPackApi;
    private final CardsPackOpeningByIdPlayerAndIdCardsPackApi cardsPackFinderByIdPlayerAndIdCardsPackApi;

    @PostMapping
    public ResponseEntity<Object> createCardsPack(@RequestBody CardsPackCreationDto dto) {
        return cardsPackApi
                .create(cardsPackCreationToDomain(dto.cardsPackType()))
                .map(CardsPackDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @GetMapping("/open/{idCardsPack}/{idPlayer}")
    public ResponseEntity<Object> openCardsPack(@PathVariable String idCardsPack, @PathVariable String idPlayer) {
        return cardsPackFinderByIdPlayerAndIdCardsPackApi
                .getDecksByCardsPackAndPlayer(idCardsPack, idPlayer)
                .map(DeckDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
