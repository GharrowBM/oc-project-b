package fr.gharrowbm.occhatopbackend.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatopMessagePostRequestDTO(
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long rentalId
) {
}
