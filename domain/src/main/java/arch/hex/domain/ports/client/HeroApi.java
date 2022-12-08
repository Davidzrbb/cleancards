package arch.hex.domain.ports.client;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.enums.CardsPackType;
import arch.hex.domain.functional.model.CardsPack;
import arch.hex.domain.functional.model.Hero;
import io.vavr.control.Either;

public interface HeroApi {
    Either<ApplicationError, Hero> create(Hero hero);
}
