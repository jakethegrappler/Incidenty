package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.UserDto;
import cz.cvut.fel.incidenty.model.Admin;
import cz.cvut.fel.incidenty.model.Employee;
import cz.cvut.fel.incidenty.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Admin toAdmin(UserDto dto);

    @Mapping(target = "id", ignore = true)
    Employee toEmployee(UserDto dto);

    @Mapping(target = "id", ignore = true)
    Student toStudent(UserDto dto);

    UserDto toDto(Admin admin);
}
