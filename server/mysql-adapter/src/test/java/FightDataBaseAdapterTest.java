import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.Rarity;
import arch.hex.domain.functional.enums.Speciality;
import arch.hex.domain.functional.model.Fight;
import arch.hex.domain.functional.model.Hero;
import arch.hex.server.adapter.FightDataBaseAdapter;
import arch.hex.server.entity.FightEntity;
import arch.hex.server.mapper.FightEntityMapper;
import arch.hex.server.repository.FightRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FightDataBaseAdapterTest {
    @InjectMocks
    private FightDataBaseAdapter fightDataBaseAdapter;
    @Mock
    private FightRepository fightRepository;

    @Nested
    class Save {
        @Captor
        private ArgumentCaptor<FightEntity> entityCaptor;

        @Test
        void should_save() {
            val heroAlly = Hero.builder().idHero(UUID.randomUUID().toString()).name("toto").speciality(Speciality.Tank).rarity(Rarity.Common).hp(10).xp(0).power(1).armor(0).level(1).build();
            val heroEnemy = Hero.builder().idHero(UUID.randomUUID().toString()).name("tata").speciality(Speciality.Tank).rarity(Rarity.Legendary).hp(100).xp(0).power(1).armor(0).level(1).build();
            val fight = Fight.builder().heroAlly(heroAlly).heroEnemy(heroEnemy).allyWin(false).build();
            val entity = FightEntityMapper.fromDomain(fight);

            when(fightRepository.save(any(FightEntity.class))).thenReturn(entity);

            val actual = fightDataBaseAdapter.save(fight);

            verify(fightRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(fightRepository);

            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(Fight.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(fight);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);

        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val heroAlly = Hero.builder().idHero(UUID.randomUUID().toString()).name("toto").speciality(Speciality.Tank).rarity(Rarity.Common).hp(10).xp(0).power(1).armor(0).level(1).build();
            val heroEnemy = Hero.builder().idHero(UUID.randomUUID().toString()).name("tata").speciality(Speciality.Tank).rarity(Rarity.Legendary).hp(100).xp(0).power(1).armor(0).level(1).build();
            val fight = Fight.builder().heroAlly(heroAlly).heroEnemy(heroEnemy).allyWin(false).build();
            val entity = FightEntityMapper.fromDomain(fight);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(fightRepository).save(any(FightEntity.class));

            val actual = fightDataBaseAdapter.save(fight);

            verify(fightRepository).save(entityCaptor.capture());
            verifyNoMoreInteractions(fightRepository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save fight", null, fight, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }

    @Nested
    class FindByIdHero {
        @Test
        void should_find_id_hero() {
            val id = UUID.randomUUID().toString();
            val heroAlly = Hero.builder().idHero(UUID.randomUUID().toString()).name("toto").speciality(Speciality.Tank).rarity(Rarity.Common).hp(10).xp(0).power(1).armor(0).level(1).build();
            val heroEnemy = Hero.builder().idHero(UUID.randomUUID().toString()).name("tata").speciality(Speciality.Tank).rarity(Rarity.Legendary).hp(100).xp(0).power(1).armor(0).level(1).build();
            val fight = Fight.builder().heroAlly(heroAlly).heroEnemy(heroEnemy).idFight(id).allyWin(false).build();
            val entity = FightEntityMapper.fromDomain(fight);

            when(fightRepository.findByHeroAlly_IdHeroOrHeroEnemy_IdHero(heroAlly.getIdHero(),heroAlly.getIdHero())).thenReturn(List.of(entity));
            val actual = fightDataBaseAdapter.findByIdHero(heroAlly.getIdHero());

            assertThat(actual.get(0)).usingRecursiveComparison().isEqualTo(fight);

            verifyNoMoreInteractions(fightRepository);

        }

        @Test
        void should_not_find_id_hero() {
            val id = UUID.randomUUID().toString();
            when(fightRepository.findByHeroAlly_IdHeroOrHeroEnemy_IdHero(id,id)).thenReturn(List.of());
            val actual = fightDataBaseAdapter.findByIdHero(id);
            assertThat(actual).isEmpty();
            verifyNoMoreInteractions(fightRepository);
        }
    }

}