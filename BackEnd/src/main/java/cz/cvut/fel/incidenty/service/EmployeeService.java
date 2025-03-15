package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.EmployeeDto;
import cz.cvut.fel.incidenty.mapper.EmployeeMapper;
import cz.cvut.fel.incidenty.model.Employee;
import cz.cvut.fel.incidenty.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        return employeeRepository.save(employee);
    }
}
