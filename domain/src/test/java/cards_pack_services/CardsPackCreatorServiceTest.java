package cards_pack_services;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.service.IdGenerationService;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackCreatorService;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardsPackCreatorServiceTest {
    @Mock
    private CardsPackPersistenceSpi cardsPackPersistenceSpi;

    @Mock
    private IdGenerationService idGenerationService;

    @InjectMocks
    private CardsPackCreatorService cardsPackCreatorService;

    @BeforeEach
    void setUp() {
        when(idGenerationService.generateNewId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    void should_create_silver_cards_pack() {
        CardsPack expectedCardsPack = CardsPack.builder()
                .idCardsPack(UUID.randomUUID().toString())
                .cardsPackType(CardsPackType.SILVER)
                .cardsNumber(3)
                .legendaryDropRate(0.05)
                .rareDropRate(0.2)
                .commonDropRate(0.75)
                .requiredTokens(1)
                .build();
        when(cardsPackPersistenceSpi.save(any(CardsPack.class))).thenReturn(Either.right(expectedCardsPack));

        Either<ApplicationError, CardsPack> result = cardsPackCreatorService.create(CardsPackType.SILVER);

        assertEquals(expectedCardsPack, result.get());
    }

    @Test
    void should_create_diamond_cards_pack() {
        CardsPack expectedCardsPack = CardsPack.builder()
                .idCardsPack(UUID.randomUUID().toString())
                .cardsPackType(CardsPackType.DIAMOND)
                .cardsNumber(5)
                .legendaryDropRate(0.15)
                .rareDropRate(0.35)
                .commonDropRate(0.5)
                .requiredTokens(2)
                .build();
        when(cardsPackPersistenceSpi.save(any(CardsPack.class))).thenReturn(Either.right(expectedCardsPack));

        Either<ApplicationError, CardsPack> result = cardsPackCreatorService.create(CardsPackType.DIAMOND);

        assertEquals(expectedCardsPack, result.get());
    }
}