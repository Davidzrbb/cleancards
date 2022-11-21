package arch.hex.client.mapper;

import arch.hex.client.dto.CardsPackDto;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;

public interface CardsPackDtoMapper {

    static CardsPackDto toDto(CardsPack cardsPack) {
        return new CardsPackDto(
                cardsPack.getIdCardsPack(),
                cardsPack.getRequiredTokens(),
                cardsPack.getCardsNumber(),
                cardsPack.getCardsPackType(),
                cardsPack.getLegendaryDropRate(),
                cardsPack.getRareDropRate(),
                cardsPack.getCommonDropRate()
        );
    }

    static CardsPackType cardsPackCreationToDomain(CardsPackType cardsPackType) {
        return CardsPack.builder().cardsPackType(cardsPackType).build().getCardsPackType();
    }
}
