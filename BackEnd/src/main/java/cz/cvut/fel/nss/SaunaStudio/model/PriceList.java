package cz.cvut.fel.nss.SaunaStudio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "PriceList.findBySaunaStudio", query = "SELECT pl FROM PriceList pl WHERE pl.saunaStudio = :saunaStudio")
})
public class PriceList extends AbstractEntity {

    @Column(nullable = false)
    @Basic(optional = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Basic(optional = false)
    private DurationType durationType;

    @ManyToOne
    private SaunaStudio saunaStudio;
}
