package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.*;
import fr.gharrowbm.occhatopbackend.services.ChatopUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ChatopUserService chatopUserService;

    public final static String BASE_PATH = "/api/auth";

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Register a new user")
    @PostMapping(BASE_PATH + "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(chatopUserService.register(registerRequestDTO));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User logged in successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Login")
    @PostMapping(BASE_PATH + "/login")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(chatopUserService.login(loginRequestDTO));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ChatopUserDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "Get self details")
    @GetMapping(BASE_PATH + "/me")
    public ResponseEntity<ChatopUserDTO> getSelfDetails(Authentication authentication) {
        return ResponseEntity.ok(chatopUserService.getByAuthentication(authentication));
    }
}
