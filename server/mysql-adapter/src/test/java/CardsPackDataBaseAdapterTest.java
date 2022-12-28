import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.server.adapter.CardsPackDataBaseAdapter;
import arch.hex.server.entity.CardsPackEntity;
import arch.hex.server.mapper.CardsPackEntityMapper;
import arch.hex.server.repository.CardsPackRepository;
import io.vavr.control.Option;
import lombok.val;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static io.vavr.API.Some;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CardsPackDataBaseAdapterTest {
    @InjectMocks
    private CardsPackDataBaseAdapter cardsPackDataBaseAdapter;
    @Mock
    private CardsPackRepository cardsPackRepository;


    @Nested
    class Save {
        @Captor
        private ArgumentCaptor<CardsPackEntity> entityCaptor;

        @Test
        void should_save() {
            val cardsPack = CardsPack.builder().build();
            val entity = CardsPackEntityMapper.fromDomain(cardsPack);

            when(cardsPackRepository.save(any(CardsPackEntity.class))).thenReturn(entity);

            val actual = cardsPackDataBaseAdapter.save(cardsPack);

            verify(cardsPackRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(cardsPackRepository);

            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(CardsPack.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(cardsPack);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val cardsPack = CardsPack.builder().build();
            val entity = CardsPackEntityMapper.fromDomain(cardsPack);
            val throwable = new IllegalArgumentException();
            when(cardsPackRepository.save(any(CardsPackEntity.class))).thenThrow(new RuntimeException());

            val actual = cardsPackDataBaseAdapter.save(cardsPack);

            verify(cardsPackRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(cardsPackRepository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save cards pack", null, cardsPack, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find() {
            val id = UUID.randomUUID().toString();
            val entity = CardsPackEntity.builder().idCardsPack(id).build();
            val cardsPack = CardsPackEntityMapper.toDomain(entity);

            when(cardsPackRepository.findCardsPackEntityByIdCardsPack(id)).thenReturn(Option.of(entity));

            val actual = cardsPackDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(cardsPack);

            verifyNoMoreInteractions(cardsPackRepository);
        }

        @Test
        void should_not_find() {
            val id = UUID.randomUUID().toString();

            when(cardsPackRepository.findCardsPackEntityByIdCardsPack(id)).thenReturn(Option.none());

            val actual = cardsPackDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isEmpty();

            verifyNoMoreInteractions(cardsPackRepository);
        }
    }

}
