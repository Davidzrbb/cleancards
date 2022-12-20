package arch.hex.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import arch.hex.infra.*;

@SpringBootApplication(scanBasePackages = {"arch.hex"})
@EnableJpaRepositories(basePackages = "arch.hex.server.repository")
@EntityScan(basePackages = "arch.hex.server.entity")
@EnableScheduling
@Import({
        CardsPackDomainConfig.class,
        DeckDomainConfig.class,
        HeroDomainConfig.class,
        PlayerDomainConfig.class,
        FightDomainConfig.class,
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


