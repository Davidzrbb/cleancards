package arch.hex.domain.ports.client.hero_api;

import arch.hex.domain.ApplicationError;
import arch.hex.domain.functional.model.Hero;
import io.vavr.control.Either;

public interface HeroCreatorApi {
    Either<ApplicationError, Hero> create(Hero hero);
}
