package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findByUsername", query = "SELECT c FROM Employee c WHERE c.email = :username"),
        @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Employee c WHERE c.email = :email"),
        @NamedQuery(name = "Customer.findByPhoneNumber", query = "SELECT c FROM Employee c WHERE c.phoneNumber = :phoneNumber")

})
public class Employee extends User{

    @Column(nullable = false)
    @Basic(optional = false)
    private String firstName;

    @Column(nullable = false)
    @Basic(optional = false)
    private String lastName;

    @Column(nullable = false)
    @Basic(optional = false)
    private String email;

    @Column(nullable = false)
    @Basic(optional = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Basic(optional = false)
    private Boolean suspended;

}
