package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "admins")
@NamedQueries({
        @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM admins a WHERE a.username = :username")
})
public class Admin extends User{

}
