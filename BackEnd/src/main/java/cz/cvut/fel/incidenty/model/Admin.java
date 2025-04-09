package cz.cvut.fel.incidenty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "admins")
public class Admin extends User {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
