package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.UserDto;
import cz.cvut.fel.incidenty.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Admin toAdmin(UserDto dto);

    UserDto toDto(Admin admin);
}
