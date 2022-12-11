package arch.hex.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Deck {
    @Default
    String idDeck = UUID.randomUUID().toString();
    Player player;
    @With
    Hero hero;
}
