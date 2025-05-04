package cz.cvut.fel.incidenty.dto;

import java.time.LocalDateTime;

public record IncidentDto(
        Long id,
        LocalDateTime date,
        LocalDateTime issueDate,
        String type,
        String position,
        String reporter,
        String izs,
        String detail,
        String solution,
        String note,
        String photoPath,
        String customPhoneNumber,

        // 🆕 souřadnice z mapy
        Integer x,
        Integer y,

        // ✅ nové pole pro označení ověření incidentu
        boolean verified
) {}
