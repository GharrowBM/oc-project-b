package fr.gharrowbm.occhatopbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatopUserDTO(
        Long id,
        String name,
        String email,

        @JsonProperty("created_at")
        String createdAt,

        @JsonProperty("updated_at")
        String updatedAt
) {
}
