package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.StudentDto;
import cz.cvut.fel.incidenty.model.Student;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Student} a {@link StudentDto}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student studentEntity);
    Student toEntity(StudentDto studentDto);
}
