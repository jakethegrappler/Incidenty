package cz.cvut.fel.incidenty.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private LocalDateTime issueDate;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String reporter;

    @Column(nullable = false)
    private String isResolved;

    private String detail;
    private String solution;
    private String note;
}
