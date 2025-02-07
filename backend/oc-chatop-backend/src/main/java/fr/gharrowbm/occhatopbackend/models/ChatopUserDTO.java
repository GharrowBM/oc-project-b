package fr.gharrowbm.occhatopbackend.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatopUserDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String createdAt,
        @NotBlank
        String updatedAt
) {
}
