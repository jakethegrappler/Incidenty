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
    }
}
