package fr.gharrowbm.occhatopbackend.models;

import fr.gharrowbm.occhatopbackend.entities.Rental;

import java.math.BigDecimal;

public record RentalDTO(
        Long id,
        String name,
        Float surface,
        Float price,
        String picture,
        String description,
        Long ownerId,
        String createdAt,
        String updatedAt
) {
}
