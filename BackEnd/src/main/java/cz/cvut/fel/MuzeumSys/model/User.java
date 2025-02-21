package cz.cvut.fel.MuzeumSys.model;

import cz.cvut.fel.nss.SaunaStudio.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
