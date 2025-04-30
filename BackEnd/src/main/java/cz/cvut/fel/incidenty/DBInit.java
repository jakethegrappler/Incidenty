package cz.cvut.fel.incidenty;

import cz.cvut.fel.incidenty.model.*;
import cz.cvut.fel.incidenty.model.enums.Role;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import cz.cvut.fel.incidenty.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final IncidentRepository incidentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        // Uživatelské účty
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setEmail("nedbal.jakub@gmail.com");
        admin.setPassword(passwordEncoder.encode("heslo"));
        admin.setRole(Role.ROLE_ADMIN);
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setPhoneNumber("123456789");

        Employee employee = new Employee();
        employee.setUsername("employee");
        employee.setEmail("employee@example.com");
        employee.setPassword(passwordEncoder.encode("employee123"));
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setFirstName("Emp");
        employee.setLastName("Loyee");
        employee.setPhoneNumber("987654321");

        Student student = new Student();
        student.setUsername("student");
        student.setEmail("student@example.com");
        student.setPassword(passwordEncoder.encode("student123"));
        student.setRole(Role.ROLE_STUDENT);
        student.setFirstName("Stud");
        student.setLastName("Ent");
        student.setPhoneNumber("555555555");

        userRepository.saveAll(List.of(admin, employee, student));

        // Ukázkový incident
        Incident incident = new Incident();
        incident.setDate(LocalDateTime.now());
        incident.setType("Napadení");
        incident.setPosition("Vchod A");
        incident.setReporter("student");
        incident.setIzs("Policie");

        incidentRepository.save(incident);

        // Ukázkové incidenty
        Incident i1 = new Incident();
        i1.setDate(LocalDateTime.now().minusDays(1));
        i1.setType("Napadení");
        i1.setPosition("A1");
        i1.setReporter("student");
        i1.setIzs("Policie");
        i1.setDetail("Došlo k fyzickému napadení u automatu.");
        i1.setCustomPhoneNumber(null);

        Incident i2 = new Incident();
        i2.setDate(LocalDateTime.now().minusDays(2));
        i2.setType("Vandalismus");
        i2.setPosition("B3");
        i2.setReporter("employee");
        i2.setIzs("Bezpečnostní služba");
        i2.setDetail("Někdo poškodil zeď ve 3. patře.");
        i2.setCustomPhoneNumber(null);
        i2.setNote("Byla pořízena fotodokumentace.");

        Incident i3 = new Incident();
        i3.setDate(LocalDateTime.now().minusDays(4));
        i3.setType("Požár");
        i3.setPosition("C1");
        i3.setReporter("Anonym");
        i3.setIzs("Hasiči");
        i3.setDetail("Hlášen kouř v učebně C126.");
        i3.setCustomPhoneNumber("777123456");

        Incident i4 = new Incident();
        i4.setDate(LocalDateTime.now().minusDays(3));
        i4.setType("Krádež");
        i4.setPosition("D2");
        i4.setReporter("student");
        i4.setIzs("Policie");
        i4.setDetail("Ztracený batoh během přednášky.");
        i4.setSolution("Případ vyšetřuje policie.");
        i4.setIssueDate(LocalDateTime.now().minusDays(1));

        Incident i5 = new Incident();
        i5.setDate(LocalDateTime.now().minusDays(7));
        i5.setType("Úraz");
        i5.setPosition("B1");
        i5.setReporter("employee");
        i5.setIzs("Záchranná služba");
        i5.setDetail("Student uklouzl na schodech.");
        i5.setSolution("Přivolána sanitka.");
        i5.setNote("Zkontrolovat protiskluzové pásky.");
        i5.setIssueDate(LocalDateTime.now().minusDays(6));

        incidentRepository.saveAll(List.of(i1, i2, i3, i4, i5));


    }
}
