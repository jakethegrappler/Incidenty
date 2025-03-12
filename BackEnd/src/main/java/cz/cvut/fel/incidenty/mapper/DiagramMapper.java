package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.DiagramDto;
import cz.cvut.fel.incidenty.model.Diagram;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Diagram} a {@link DiagramDto}.
 */
@Mapper(componentModel = "spring")
public interface DiagramMapper {
    DiagramDto toDto(Diagram diagramEntity);
    Diagram toEntity(DiagramDto diagramDto);
}
