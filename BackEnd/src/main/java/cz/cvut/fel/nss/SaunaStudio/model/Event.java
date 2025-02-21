package cz.cvut.fel.nss.SaunaStudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Event.findAllAvailable", query = "SELECT c FROM Customer c WHERE c.email = :email")
})
public class Event extends AbstractEntity {

    @Column(nullable = false)
    @Basic(optional = false)
    private String name;

    @Column()
    @Basic()
    private String description;

    @Column(nullable = false)
    @Basic(optional = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    @Basic(optional = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    @Basic(optional = false)
    private Integer totalCapacity;

    @Column(nullable = false)
    @Basic(optional = false)
    private Integer freeCapacity;

    @Column(nullable = false)
    @Basic(optional = false)
    private Boolean isAvailable;

    @Column()
    @Basic()
    private String imgFolderURL;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<SaunaEvent> saunaEvents;
}
