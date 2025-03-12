package cz.cvut.fel.incidenty.dto;

public record EmployeeDto (
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber
){
}
