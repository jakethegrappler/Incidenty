package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "SaunaStudio.findByName", query = "SELECT ss FROM SaunaStudio ss WHERE ss.name = :name"),
        @NamedQuery(name = "SaunaStudio.findByURL", query = "SELECT ss FROM SaunaStudio ss WHERE ss.URL = :URL")
})
public class SaunaStudio extends AbstractEntity {

    @Column(nullable = false)
    @Basic(optional = false)
    private String URL;

    @Column(nullable = false)
    @Basic(optional = false)
    private String name;

    @Column(nullable = false)
    @Basic(optional = false)
    private Integer capacity;

    @Column(nullable = false)
    @Basic(optional = false)
    private Integer phoneNumber;

    @Column(nullable = false)
    @Basic(optional = false)
    private String email;

    @Column(nullable = false)
    @Basic(optional = false)
    private String address;

}
