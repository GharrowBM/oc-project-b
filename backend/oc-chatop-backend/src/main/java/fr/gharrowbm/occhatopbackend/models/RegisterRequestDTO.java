package fr.gharrowbm.occhatopbackend.models;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Password is mandatory")
        String password

) {
}
