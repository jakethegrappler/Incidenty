package cz.cvut.fel.incidenty.repository;

import cz.cvut.fel.incidenty.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
