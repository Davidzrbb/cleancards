package arch.hex.domain.functional.service.validation;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Deck;
import arch.hex.domain.functional.model.Hero;
import arch.hex.domain.functional.model.Player;
import arch.hex.domain.functional.service.deck_services.DeckFinderByIdService;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FightValidator {

    private final DeckFinderByIdService deckFinderByIdService;

    public Validation<ApplicationError, List<Deck>> validateFight(String idAlly, String idEnemy) {
        List<Deck> decks = new ArrayList<>();
        decks.add(deckFinderByIdService.findById(idAlly).get());
        decks.add(deckFinderByIdService.findById(idEnemy).get());
        Hero heroAlly = decks.get(0).getHero();
        Hero heroEnemy = decks.get(1).getHero();
        Player playerAlly = decks.get(0).getPlayer();
        Player playerEnemy = decks.get(1).getPlayer();
        if (decks.contains(null)) {
            return Validation.invalid(new ApplicationError("Deck not found", null, null, null));
        } else if (heroAlly == null || heroEnemy == null) {
            return Validation.invalid(new ApplicationError("Hero not found", null, null, null));
        } else if (heroAlly.getLevel() > heroEnemy.getLevel()) {
            return Validation.invalid(new ApplicationError("Hero level is too high", null, null, null));
        } else if (playerAlly == null || playerEnemy == null) {
            return Validation.invalid(new ApplicationError("Player not found", null, null, null));
        } else if (playerAlly.getIdPlayer().equals(playerEnemy.getIdPlayer())) {
            return Validation.invalid(new ApplicationError("Player can't fight with himself", null, null, null));
        }
        return Validation.valid(decks);
    }
}
