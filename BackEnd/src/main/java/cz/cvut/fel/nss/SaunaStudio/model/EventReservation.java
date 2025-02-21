package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EventReservation extends AbstractEntity {

    @Column(nullable = false)
    @Basic(optional = false)
    private int numOfPeople;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Event event;
}
