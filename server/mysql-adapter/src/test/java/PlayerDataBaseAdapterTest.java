import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Player;
import arch.hex.server.adapter.PlayerDataBaseAdapter;
import arch.hex.server.entity.PlayerEntity;
import arch.hex.server.mapper.PlayerEntityMapper;
import arch.hex.server.repository.PlayerRepository;
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

import java.util.UUID;

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerDataBaseAdapterTest {
    @InjectMocks
    private PlayerDataBaseAdapter playerDataBaseAdapter;
    @Mock
    private PlayerRepository playerRepository;

    @Nested
    class Save {
        @Captor
        private ArgumentCaptor<PlayerEntity> entityCaptor;

        @Test
        void should_save() {
            val player = Player.builder().build();
            val entity = PlayerEntityMapper.fromDomain(player);

            when(playerRepository.save(any(PlayerEntity.class))).thenReturn(entity);
            val actual = playerDataBaseAdapter.save(player);

            verify(playerRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(playerRepository);


            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(Player.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(player);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val player = Player.builder().build();
            val entity = PlayerEntityMapper.fromDomain(player);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(playerRepository).save(any(PlayerEntity.class));

            val actual = playerDataBaseAdapter.save(player);

            verify(playerRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(playerRepository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save player", null, player, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find_by_id() {
            val id = UUID.randomUUID().toString();
            val entity = PlayerEntity.builder().build();
            val domain = PlayerEntityMapper.toDomain(entity);

            when(playerRepository.findPlayerEntityByIdPlayer(id)).thenReturn(Some(entity));

            val actual = playerDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(domain);

            verifyNoMoreInteractions(playerRepository);
        }

        @Test
        void should_not_find() {
            val id = UUID.randomUUID().toString();

            when(playerRepository.findPlayerEntityByIdPlayer(id)).thenReturn(None());

            val actual = playerDataBaseAdapter.findById(id);

            VavrAssertions.assertThat(actual).isEmpty();

            verifyNoMoreInteractions(playerRepository);
        }
    }
}
