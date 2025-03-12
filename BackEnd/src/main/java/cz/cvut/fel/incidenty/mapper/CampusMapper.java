package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.CampusDto;
import cz.cvut.fel.incidenty.model.Campus;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Campus} a {@link CampusDto}.
 */
@Mapper(componentModel = "spring")
public interface CampusMapper {
    CampusDto toDto(Campus campusEntity);
    Campus toEntity(CampusDto campusDto);
}
