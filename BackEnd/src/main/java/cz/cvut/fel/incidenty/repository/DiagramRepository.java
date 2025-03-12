package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.Diagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagramRepository extends JpaRepository<Diagram, Long> {

}
