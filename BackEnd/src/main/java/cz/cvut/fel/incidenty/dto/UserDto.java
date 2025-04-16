package cz.cvut.fel.incidenty.dto;

import cz.cvut.fel.incidenty.model.enums.Role;

public record UserDto(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        Role role,
        String phoneNumber
) {}
