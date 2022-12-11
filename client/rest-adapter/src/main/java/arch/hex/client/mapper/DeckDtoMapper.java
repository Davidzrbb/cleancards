package arch.hex.client.mapper;

import arch.hex.client.dto.deck_dto.DeckDto;
import arch.hex.client.dto.hero_dto.HeroDto;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import io.vavr.collection.Set;

public interface DeckDtoMapper {

    static DeckDto toDto(Deck deck) {
        return new DeckDto(
                deck.getIdDeck(),
                deck.getPlayer(),
                deck.getHero()
        );
    }

    static Set<DeckDto> toDto(Set<Deck> decks) {
        return decks.map(DeckDtoMapper::toDto);
    }

}
