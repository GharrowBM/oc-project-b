package fr.gharrowbm.occhatopbackend.models;

public record ChatopUserDTO(
        Long id,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {
}
