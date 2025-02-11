package fr.gharrowbm.occhatopbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.gharrowbm.occhatopbackend.entities.Rental;

import java.math.BigDecimal;

public record RentalDTO(
        Long id,
        String name,
        Float surface,
        Float price,
        String picture,
        String description,

        @JsonProperty("owner_id")
        Long ownerId,

        @JsonProperty("created_at")
        String createdAt,

        @JsonProperty("updated_at")
        String updatedAt
) {
}
