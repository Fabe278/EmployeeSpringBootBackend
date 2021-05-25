package at.fhainzinger.config;

import at.fhainzinger.data.EmployeeEntity;
import at.fhainzinger.database.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
            if(repository.count() == 0){
                log.info("Preloading " + repository.save(new EmployeeEntity(1, "Fabian Hainzinger", "HainzSecret", "13.54162555","48.4111791")));
                log.info("Preloading " + repository.save(new EmployeeEntity(2, "Matthias Schneglberger", "SchneglSecret", "13.577984","48.268140")));
                log.info("Preloading " + repository.save(new EmployeeEntity(3, "Benjamin Heissenberger", "HeisseSecret", "13.658687","48.445740")));
            }
        };
    }
}
