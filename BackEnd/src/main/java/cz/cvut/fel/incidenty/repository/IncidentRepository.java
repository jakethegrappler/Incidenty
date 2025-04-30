package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    // ⬇️ Přidaná metoda pro načtení incidentů podle pozice (sektoru)
        List<Incident> findByPositionIgnoreCase(String position);
}
