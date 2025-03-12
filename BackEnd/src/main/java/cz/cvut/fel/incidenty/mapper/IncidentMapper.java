package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.model.Incident;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Incident} a {@link IncidentDto}.
 */
@Mapper(componentModel = "spring")
public interface IncidentMapper {
    IncidentDto toDto(Incident incidentEntity);
    Incident toEntity(IncidentDto incidentDto);
}
