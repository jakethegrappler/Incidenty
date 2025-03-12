package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {

}
