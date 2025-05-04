package cz.cvut.fel.incidenty.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vyplňuje se hned při nahlášení
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String izs;

    @Column
    private String reporter;

    // Vyplňuje se později při řešení
    private LocalDateTime issueDate;

    private String detail;

    private String solution;

    private String note;

    private String photoPath;

    private String customPhoneNumber;

    // 🆕 Souřadnice kliknutí na mapě
    private Integer x;
    private Integer y;

    // Nové pole pro označení ověření incidentu
    @Column(nullable = false)
    private boolean verified = false;
}
