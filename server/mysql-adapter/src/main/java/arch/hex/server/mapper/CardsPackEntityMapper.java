package arch.hex.server.mapper;

import arch.hex.server.entity.CardsPackEntity;
import arch.hex.domain.functional.model.CardsPack;

public interface CardsPackEntityMapper {
    static CardsPack toDomain(CardsPackEntity entity) {
        return CardsPack.builder()
                .idCardsPack(entity.getIdCardsPack())
                .requiredTokens(entity.getRequiredTokens())
                .cardsNumber(entity.getCardsNumber())
                .cardsPackType(entity.getType())
                .legendaryDropRate(entity.getLegendaryDropRate())
                .rareDropRate(entity.getRareDropRate())
                .commonDropRate(entity.getCommonDropRate())
                .build();
    }

    static CardsPackEntity fromDomain(CardsPack domain) {
        return CardsPackEntity.builder()
                .idCardsPack(domain.getIdCardsPack())
                .requiredTokens(domain.getRequiredTokens())
                .cardsNumber(domain.getCardsNumber())
                .type(domain.getCardsPackType())
                .legendaryDropRate(domain.getLegendaryDropRate())
                .rareDropRate(domain.getRareDropRate())
                .commonDropRate(domain.getCommonDropRate())
                .build();
    }
}
