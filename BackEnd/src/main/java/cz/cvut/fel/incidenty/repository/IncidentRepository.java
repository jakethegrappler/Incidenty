package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
