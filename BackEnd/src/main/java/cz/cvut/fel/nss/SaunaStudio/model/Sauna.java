package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@NamedQueries({
        @NamedQuery(name = "Sauna.findBySaunaStudio", query = "SELECT s FROM Sauna s WHERE s.name = :saunaStudio"),
        @NamedQuery(name = "Sauna.findBySaunaType", query = "SELECT s FROM Sauna s WHERE s.saunaType = :saunaType"),
        @NamedQuery(name = "Sauna.findByMaxTemperature", query = "SELECT s FROM Sauna s WHERE s.maxTemp = :maxTemperature"),
        @NamedQuery(name = "Sauna.findByName", query = "SELECT ss FROM Sauna ss WHERE ss.name = :name")

})
public class Sauna extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Basic(optional = false)
    private SaunaType saunaType;

    @Column(nullable = false)
    @Basic(optional = false)
    private String name;

    @Column(nullable = true)
    @Basic
    private Integer maxTemp;

    @Column(nullable = true)
    @Basic
    private String imgFolderURL;
}
