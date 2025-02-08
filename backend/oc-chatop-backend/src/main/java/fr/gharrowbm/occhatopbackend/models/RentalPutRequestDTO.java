package fr.gharrowbm.occhatopbackend.models;

import org.springframework.web.multipart.MultipartFile;

public record RentalPutRequestDTO(
        String name,
        String description,
        Float price,
        Float surface
) {
}
