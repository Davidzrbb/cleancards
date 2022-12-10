package arch.hex.server.mapper;

import arch.hex.domain.functional.model.Deck;
import arch.hex.server.entity.DeckEntity;

public interface DeckEntityMapper {
    static Deck toDomain(DeckEntity entity) {
        //hero can be null
        return Deck.builder()
                .idDeck(entity.getIdDeck())
                .player(PlayerEntityMapper.toDomain(entity.getPlayer()))
                .hero(entity.getHero() != null ? HeroEntityMapper.toDomain(entity.getHero()) : null)
                .build();
    }

    static DeckEntity fromDomain(Deck domain) {
        //hero can be null
        return DeckEntity.builder()
                .idDeck(domain.getIdDeck())
                .hero(domain.getHero() != null ? HeroEntityMapper.fromDomain(domain.getHero()) : null)
                .player(PlayerEntityMapper.fromDomain(domain.getPlayer()))
                .build();
    }
}

