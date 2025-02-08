package fr.gharrowbm.occhatopbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatopMessagePostRequestDTO(
        @NotBlank
        String message,
        @NotNull
        @JsonProperty("user_id")
        Long userId,
        @NotNull
        @JsonProperty("rental_id")
        Long rentalId
) {
}
