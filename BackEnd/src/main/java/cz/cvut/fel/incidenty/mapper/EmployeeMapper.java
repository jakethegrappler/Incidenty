package cz.cvut.fel.incidenty.mapper;

import cz.cvut.fel.incidenty.dto.EmployeeDto;
import cz.cvut.fel.incidenty.model.Employee;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Employee} a {@link EmployeeDto}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employeeEntity);
    Employee toEntity(EmployeeDto employeeDto);
}
