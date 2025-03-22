package cz.cvut.fel.incidenty.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "admin")
public class Admin extends User {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
