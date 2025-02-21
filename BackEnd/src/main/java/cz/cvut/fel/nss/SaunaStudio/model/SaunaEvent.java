package cz.cvut.fel.nss.SaunaStudio.model;

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
public class SaunaEvent extends AbstractEntity {

    @ManyToOne
    private Sauna sauna;

    @ManyToOne
    private Event event;
}
