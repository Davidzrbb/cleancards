import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.model.Hero;
import arch.hex.server.adapter.HeroDataBaseAdapter;
import arch.hex.server.entity.HeroEntity;
import arch.hex.server.mapper.HeroEntityMapper;
import arch.hex.server.repository.HeroRepository;
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

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroDataBaseAdapterTest {
    @InjectMocks
    private HeroDataBaseAdapter heroDataBaseAdapter;
    @Mock
    private HeroRepository heroRepository;

    @Nested
    class Save {
        @Captor
        private ArgumentCaptor<HeroEntity> entityCaptor;

        @Test
        void should_save() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);

            when(heroRepository.save(any(HeroEntity.class))).thenReturn(entity);
            val actual = heroDataBaseAdapter.save(hero);

            verify(heroRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(heroRepository);

            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(Hero.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(hero);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(heroRepository).save(any(HeroEntity.class));

            val actual = heroDataBaseAdapter.save(hero);

            verify(heroRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(heroRepository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save hero", null, hero, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }

    @Nested
    class FindAll {
        @Test
        void should_find_all() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);

            when(heroRepository.findAll()).thenReturn(List.of(entity));
            val actual = heroDataBaseAdapter.findAll();

            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get().get(0)).usingRecursiveComparison().isEqualTo(hero);

            verifyNoMoreInteractions(heroRepository);
        }

        @Test
        void should_not_find_all() {
            when(heroRepository.findAll()).thenReturn(List.of());
            val actual = heroDataBaseAdapter.findAll();
            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get()).isEmpty();
            verifyNoMoreInteractions(heroRepository);
        }
    }

    @Nested
    class FindByRarity {
        @Test
        void should_find_by_rarity() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);


            when(heroRepository.findHeroEntityByRarity(any(Rarity.class))).thenReturn(Some(entity));
            val actual = heroDataBaseAdapter.findByRarity(Rarity.Common);
            VavrAssertions.assertThat(actual).isDefined();
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(hero);

            verifyNoMoreInteractions(heroRepository);
        }

        @Test
        void should_not_find_by_rarity() {
            when(heroRepository.findHeroEntityByRarity(any(Rarity.class))).thenReturn(None());
            val actual = heroDataBaseAdapter.findByRarity(Rarity.Common);
            VavrAssertions.assertThat(actual).isEmpty();
            verifyNoMoreInteractions(heroRepository);
        }
    }
}
