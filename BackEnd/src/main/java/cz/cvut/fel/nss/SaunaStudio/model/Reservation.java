package cz.cvut.fel.nss.SaunaStudio.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document(indexName = "reservations")  // Index name in Elasticsearch
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findByCustomer", query = "SELECT r FROM Reservation r WHERE r.customer = :customer"),
        @NamedQuery(name = "Reservation.findByDay", query = "SELECT r FROM Reservation r WHERE r.startTime = :day"),
        @NamedQuery(name = "Reservation.findByHour", query = "SELECT r FROM Reservation r WHERE r.startTime = :hour")

})
public class Reservation extends AbstractEntity {

    @Field(type = FieldType.Date)
    @Column(nullable = false)
    @Basic(optional = false)
    private LocalDateTime startTime;

    @Field(type = FieldType.Date)
    @Column(nullable = false)
    @Basic(optional = false)
    private LocalDateTime endTime;

    @Field(type = FieldType.Integer)
    @Column(nullable = false)
    @Basic(optional = false)
    private Integer numOfPeople;

    @Field(type = FieldType.Keyword)
    @Enumerated(EnumType.STRING)
    @Column
    @Basic
    private ReservationStateType stateType;

    @Field(type = FieldType.Object)
    @ManyToOne
    private Customer customer;

    @Field(type = FieldType.Object)
    @ManyToOne
    private Event event;

    @Field(type = FieldType.Object)
    @ManyToOne
    private SaunaStudio saunaStudio;
}
