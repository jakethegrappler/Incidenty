package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
