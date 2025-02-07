package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.ChatopUserDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;
import fr.gharrowbm.occhatopbackend.services.ChatopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ChatopUserService chatopUserService;

    public final static String BASE_PATH = "/api/auth";

    @PostMapping(BASE_PATH + "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(chatopUserService.register(registerRequestDTO));
    }

    @PostMapping(BASE_PATH + "/login")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(chatopUserService.login(loginRequestDTO));
    }

    @GetMapping(BASE_PATH + "/me")
    public ResponseEntity<ChatopUserDTO> getSelfDetails(Authentication authentication) {
        return ResponseEntity.ok(chatopUserService.getByAuthentication(authentication));
    }
}
