package cz.cvut.fel.incidenty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cz.cvut.fel.incidenty")
public class IncidentyApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncidentyApplication.class, args);
    }
}
