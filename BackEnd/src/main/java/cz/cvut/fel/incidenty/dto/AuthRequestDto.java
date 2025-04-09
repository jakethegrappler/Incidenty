package cz.cvut.fel.incidenty.dto;

public record AuthRequestDto(
        String email,
        String password
) {}
