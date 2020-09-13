package test_task.polyclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PolyclinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolyclinicApplication.class, args);
    }

}
