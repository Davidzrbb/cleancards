package arch.hex.client.resource;


import arch.hex.client.mapper.DeckDtoMapper;
import arch.hex.domain.ports.client.deck_api.DeckFightApi;
import arch.hex.domain.ports.client.deck_api.DeckFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/deck")
public class DeckResource {
    private final DeckFinderApi deckFinderApi;
    private final DeckFightApi deckFightApi;

    @GetMapping("player/{idPlayer}")
    public ResponseEntity<Object> findByIdPlayer(@PathVariable String idPlayer) {
        return deckFinderApi
                .findByIdPlayer(idPlayer)
                .map(DeckDtoMapper::toDto)
                .fold(ResponseEntity.notFound()::build, ResponseEntity::ok);
    }

    @GetMapping("fight/{idDeckPlayer}/{idDeckEnemy}")
    public ResponseEntity<Object> fight(@PathVariable String idDeckPlayer, @PathVariable String idDeckEnemy) {
        return deckFightApi
                .fight(idDeckPlayer, idDeckEnemy)
                .fold(error -> ResponseEntity.badRequest().body(error), ResponseEntity::ok);
    }
}