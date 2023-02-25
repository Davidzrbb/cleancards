package arch.hex.client.dto.deck_dto;

import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record DeckDto(
        String idDeck,
        Player player,

        Hero hero

) {
}
