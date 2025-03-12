package cz.cvut.fel.incidenty.dto;

public record AdminDto (
    Long id,
    String firstName,
    String lastName,
    String email,
    String password

){}
