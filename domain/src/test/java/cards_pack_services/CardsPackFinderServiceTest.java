package cards_pack_services;

import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.service.cards_pack_services.CardsPackFinderService;
import arch.hex.domain.ports.server.model_persistence.CardsPackPersistenceSpi;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CardsPackFinderServiceTest {

    @Mock
    private CardsPackPersistenceSpi cardsPackPersistenceSpi;

    @InjectMocks
    private CardsPackFinderService cardsPackFinderService;

    @Test
    void testFindByIdWhenCardsPackExists() {
        CardsPack cardsPack1 = CardsPack.builder().idCardsPack("1").build();
        when(cardsPackPersistenceSpi.findById(cardsPack1.getIdCardsPack())).thenReturn(Option.of(cardsPack1));

        Option<CardsPack> result = cardsPackFinderService.findById(cardsPack1.getIdCardsPack());

        assertTrue(result.isDefined());
        assertEquals(cardsPack1, result.get());
        verify(cardsPackPersistenceSpi).findById(cardsPack1.getIdCardsPack());
        verifyNoMoreInteractions(cardsPackPersistenceSpi);
    }

    @Test
    void testFindByIdWhenCardsPackDoesNotExist() {
        CardsPack cardsPack1 = CardsPack.builder().idCardsPack("1").build();
        when(cardsPackPersistenceSpi.findById(cardsPack1.getIdCardsPack())).thenReturn(Option.none());

        Option<CardsPack> result = cardsPackFinderService.findById(cardsPack1.getIdCardsPack());

        assertTrue(result.isEmpty());
        verify(cardsPackPersistenceSpi).findById(cardsPack1.getIdCardsPack());
        verifyNoMoreInteractions(cardsPackPersistenceSpi);
    }
}