package cz.cvut.fel.incidenty.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Data Transfer Object for Incident entity.
 */

public record IncidentDto(
        Long id,
        LocalDateTime date,
        LocalDateTime issueDate,
        String type,
        String position,
        String reporter,
        String izs, //IZS=informovana zachranna slozka
        String detail,
        String solution,
        String note,
        String photoPath


) {

}
