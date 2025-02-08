package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPostRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPutRequestDTO;
import fr.gharrowbm.occhatopbackend.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    public final static String BASE_PATH = "/api/rentals";

    @GetMapping(BASE_PATH)
    public ResponseEntity<List<RentalDTO>> getAll() {
        return ResponseEntity.ok(rentalService.getAll());
    }

    @GetMapping(BASE_PATH + "/{id}")
    public ResponseEntity<RentalDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getById(id));
    }

    @PostMapping(BASE_PATH)
    public ResponseEntity<BaseMessageResponse> post(RentalPostRequestDTO rentalPostRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(rentalService.create(rentalPostRequestDTO, email));
    }

    @PutMapping(BASE_PATH + "/{id}")
    public ResponseEntity<BaseMessageResponse> put(@PathVariable Long id, RentalPutRequestDTO rentalPutRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(rentalService.update(id, rentalPutRequestDTO, email));
    }
}
