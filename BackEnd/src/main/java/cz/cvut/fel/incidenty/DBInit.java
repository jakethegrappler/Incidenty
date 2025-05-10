package cz.cvut.fel.incidenty;

import cz.cvut.fel.incidenty.model.Admin;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import cz.cvut.fel.incidenty.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

import static cz.cvut.fel.incidenty.model.enums.Role.ROLE_ADMIN;

@Component
@RequiredArgsConstructor
public class DBInit {

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final List<String> types = Arrays.asList(
            "Krádež", "Napadení", "Požár", "Úraz", "Vandalismus", "Havárie"
    );

    private final List<String> izsUnits = Arrays.asList("PČR", "HZS", "ZZSHMP", "Ne");

    private final Random random = new Random();

    private static final Map<String, List<int[]>> sectorPoints = Map.ofEntries(
            Map.entry("A3", List.of(new int[]{419, 346}, new int[]{474, 358}, new int[]{451, 363}, new int[]{454, 372}, new int[]{441, 339})),
            Map.entry("A4", List.of(new int[]{431, 471}, new int[]{448, 464}, new int[]{457, 476}, new int[]{469, 472}, new int[]{468, 496})),
            Map.entry("B2", List.of(new int[]{548, 298}, new int[]{596, 316}, new int[]{523, 301}, new int[]{614, 290}, new int[]{538, 300})),
            Map.entry("B3", List.of(new int[]{603, 427}, new int[]{499, 408}, new int[]{562, 417}, new int[]{583, 407}, new int[]{553, 412})),
            Map.entry("C2", List.of(new int[]{508, 257}, new int[]{506, 269}, new int[]{496, 224}, new int[]{487, 234}, new int[]{514, 228})),
            Map.entry("C3", List.of(new int[]{485, 319}, new int[]{517, 388}, new int[]{490, 405}, new int[]{504, 385}, new int[]{496, 362})),
            Map.entry("C4", List.of(new int[]{506, 457}, new int[]{498, 461}, new int[]{510, 480}, new int[]{519, 447}, new int[]{485, 492})),
            Map.entry("D2", List.of(new int[]{464, 314}, new int[]{462, 296}, new int[]{493, 286}, new int[]{482, 296}, new int[]{486, 285})),
            Map.entry("D3", List.of(new int[]{469, 430}, new int[]{490, 409}, new int[]{454, 427}, new int[]{486, 427}, new int[]{460, 424})),
            Map.entry("E1", List.of(new int[]{639, 414}, new int[]{690, 349}, new int[]{687, 343}, new int[]{632, 399}, new int[]{725, 322})),
            Map.entry("F1", List.of(new int[]{632, 548}, new int[]{607, 515}, new int[]{651, 514}, new int[]{641, 501}, new int[]{642, 496})),
            Map.entry("G1", List.of(new int[]{753, 430}, new int[]{719, 474}, new int[]{739, 442}, new int[]{691, 493}, new int[]{725, 466})),
            Map.entry("H1", List.of(new int[]{735, 352}, new int[]{659, 429}, new int[]{693, 392}, new int[]{731, 399}, new int[]{718, 395})),
            Map.entry("B1A", List.of(new int[]{734, 545}, new int[]{751, 514}, new int[]{771, 503}, new int[]{781, 528}, new int[]{788, 458})),
            Map.entry("B3A", List.of(new int[]{835, 407}, new int[]{898, 389}, new int[]{911, 394}, new int[]{831, 428}, new int[]{899, 375}))
    );

    @PostConstruct
    public void initData() {
        for (Map.Entry<String, List<int[]>> entry : sectorPoints.entrySet()) {
            String sector = entry.getKey();
            List<int[]> points = entry.getValue();

            for (int i = 0; i < points.size(); i++) {
                int[] coords = points.get(i);
                Incident incident = new Incident();
                incident.setType(getRandom(types));
                incident.setPosition(sector);
                incident.setX(coords[0]);
                incident.setY(coords[1]);
                incident.setReporter("tester@fel.cvut.cz");
                incident.setDetail("Testovací incident #" + (i + 1) + " v sektoru " + sector);
                incident.setDate(LocalDateTime.now().minusDays(random.nextInt(30)));
                incident.setIzs(getRandom(izsUnits));
                incident.setCustomPhoneNumber("123456789");
                incident.setVerified(false);

                incidentRepository.save(incident);
            }
        }

        Admin admin = new Admin();
        admin.setUsername("nedbajak");
        admin.setRole(ROLE_ADMIN);
        admin.setPassword(passwordEncoder.encode("heslo"));
        admin.setPhoneNumber("776281583");
        admin.setEmail("nedbal.jakub@gmail.com");
        admin.setFirstName("Jakub");
        admin.setLastName("Nedbal");

        userRepository.save(admin);
    }

    private <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
