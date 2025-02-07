package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.ChatopUserDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;
import fr.gharrowbm.occhatopbackend.services.ChatopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ChatopUserService chatopUserService;

    public final static String BASE_PATH = "/api/auth";

    @PostMapping(BASE_PATH + "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(BASE_PATH + "/login")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(BASE_PATH + "/me")
    public ResponseEntity<ChatopUserDTO> getSelfDetails() {
        return ResponseEntity.ok().build();
    }
}
