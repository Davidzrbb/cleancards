import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Player;
import arch.hex.server.adapter.DeckDataBaseAdapter;
import arch.hex.server.entity.DeckEntity;
import arch.hex.server.entity.PlayerEntity;
import arch.hex.server.mapper.DeckEntityMapper;
import arch.hex.server.repository.DeckRepository;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DeckDataBaseAdapterTest {
    @InjectMocks
    private DeckDataBaseAdapter deckDataBaseAdapter;
    @Mock
    private DeckRepository deckRepository;

    @Nested
    class Save {
        @Captor
        private ArgumentCaptor<DeckEntity> entityCaptor;

        @Test
        void should_save() {
            val player = Player.builder().idPlayer(UUID.randomUUID().toString()).pseudo("toto").winCount(0).tokens(4).build();
            val deck = Deck.builder().player(player).build();
            val entity = DeckEntityMapper.fromDomain(deck);

            when(deckRepository.save(any(DeckEntity.class))).thenReturn(entity);

            val actual = deckDataBaseAdapter.save(deck);

            verify(deckRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(deckRepository);

            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(Deck.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(deck);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val player = Player.builder().idPlayer(UUID.randomUUID().toString()).pseudo("toto").winCount(0).tokens(4).build();
            val deck = Deck.builder().player(player).build();
            val entity = DeckEntityMapper.fromDomain(deck);
            val throwable = new IllegalArgumentException();

            when(deckRepository.save(any(DeckEntity.class))).thenThrow(new RuntimeException());

            val actual = deckDataBaseAdapter.save(deck);

            verify(deckRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(deckRepository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save deck", null, deck, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find() {
            val id = UUID.randomUUID().toString();
            val player = PlayerEntity.builder().idPlayer(UUID.randomUUID().toString()).pseudo("toto").winCount(0).tokens(4).build();
            val entity = DeckEntity.builder().idDeck(id).player(player).build();
            val domain = DeckEntityMapper.toDomain(entity);

            when(deckRepository.findDeckEntityByIdDeck(id)).thenReturn(Option.of(entity));
            val actual = deckDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(domain);

            verifyNoMoreInteractions(deckRepository);

        }

        @Test
        void should_not_find() {
            val id = UUID.randomUUID().toString();
            when(deckRepository.findDeckEntityByIdDeck(id)).thenReturn(Option.none());
            val actual = deckDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isEmpty();
            verifyNoMoreInteractions(deckRepository);
        }
    }

    @Nested
    class FindByIdPlayer {
        @Test
        void should_find_id_player() {
            val id = UUID.randomUUID().toString();
            val player = PlayerEntity.builder().idPlayer(UUID.randomUUID().toString()).pseudo("toto").winCount(0).tokens(4).build();
            val entity = DeckEntity.builder().idDeck(id).player(player).build();
            val domain = DeckEntityMapper.toDomain(entity);

            when(deckRepository.findByPlayer_IdPlayer(player.getIdPlayer())).thenReturn(List.of(entity));
            val actual = deckDataBaseAdapter.findByIdPlayer(player.getIdPlayer());

            assertThat(actual.get(0)).usingRecursiveComparison().isEqualTo(domain);

            verifyNoMoreInteractions(deckRepository);

        }

        @Test
        void should_not_find_id_player() {
            val id = UUID.randomUUID().toString();
            when(deckRepository.findByPlayer_IdPlayer(id)).thenReturn(List.of());
            val actual = deckDataBaseAdapter.findByIdPlayer(id);
            assertThat(actual).isEmpty();
            verifyNoMoreInteractions(deckRepository);
        }
    }
}
