package cz.cvut.fel.incidenty;

import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DBInit {

    private final IncidentRepository incidentRepository;

    private final List<String> sectors = Arrays.asList(
            "A3", "A4", "B2", "B3", "C2", "C3", "C4", "D2", "D3",
            "E1", "F1", "G1", "H1", "B1A", "B3A"
    );

    private final List<String> types = Arrays.asList(
            "Krádež", "Napadení", "Požár", "Úraz", "Vandalismus", "Havárie"
    );

    private final List<String> izsUnits = Arrays.asList("PČR", "HZS", "ZZSHMP", "Ne");

    private final Random random = new Random();

    @PostConstruct
    public void initData() {
        for (String sector : sectors) {
            for (int i = 0; i < 5; i++) {
                Incident incident = new Incident();
                incident.setType(getRandom(types));
                incident.setPosition(sector);
                incident.setReporter("tester@fel.cvut.cz");
                incident.setDetail("Testovací incident #" + (i + 1) + " v sektoru " + sector);
                incident.setDate(LocalDateTime.now().minusDays(random.nextInt(30)));
                incident.setIzs(getRandom(izsUnits));
                incident.setCustomPhoneNumber("123456789");

                // Nepovinné atributy necháme null: issueDate, note, solution
                incidentRepository.save(incident);
            }
        }
    }

    private <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
