package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.*;
import fr.gharrowbm.occhatopbackend.services.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    public final static String BASE_PATH = "/api/rentals";

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All rentals retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = RentalDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Get all rentals")
    @GetMapping(BASE_PATH)
    public ResponseEntity<List<RentalDTO>> getAll() {
        return ResponseEntity.ok(rentalService.getAll());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rental retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RentalDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Get a rental by ID")
    @GetMapping(BASE_PATH + "/{id}")
    public ResponseEntity<RentalDTO> getById(@Parameter(required = true, description = "The ID of a rental") @PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getById(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rental created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
    })
    @Operation(summary = "Create a new rental")
    @PostMapping(BASE_PATH)
    public ResponseEntity<BaseMessageResponse> post(@Parameter(required = true, description = "Rental informations for the creation of a new rental") RentalPostRequestDTO rentalPostRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(rentalService.create(rentalPostRequestDTO, email));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Update a rental by ID")
    @PutMapping(BASE_PATH + "/{id}")
    public ResponseEntity<BaseMessageResponse> put(@Parameter(required = true, description = "The ID of a rental") @PathVariable Long id, @Parameter(required = true, description = "Rental informations for the modification of an existing rental") RentalPutRequestDTO rentalPutRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(rentalService.update(id, rentalPutRequestDTO, email));
    }
}
