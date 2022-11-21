package arch.hex.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Deck {
    @Default
    UUID idDeck = UUID.randomUUID();
}
