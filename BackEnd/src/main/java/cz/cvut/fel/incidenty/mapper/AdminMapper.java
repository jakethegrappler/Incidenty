package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.AdminDto;
import cz.cvut.fel.incidenty.model.Admin;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Admin} a {@link AdminDto}.
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDto toDto(Admin adminEntity);
    Admin toEntity(AdminDto adminDto);
}
