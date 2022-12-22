package arch.hex.client.mapper;

import arch.hex.client.dto.deck_dto.DeckDto;

import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.model.Player;

import java.util.List;


public interface DeckDtoMapper {

    static DeckDto toDto(Deck deck) {
        return new DeckDto(
                deck.getIdDeck(),
                deck.getPlayer(),
                deck.getHero()
        );
    }

    static Object toDto(List<Deck> decks) {
        return decks.stream()
                .map(DeckDtoMapper::toDto)
                .toList();
    }

    static Object toDto(String s) {
        return s;
    }
}
