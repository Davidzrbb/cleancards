package arch.hex.bootstrap;

import arch.hex.bootstrap.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = {"arch.hex"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


