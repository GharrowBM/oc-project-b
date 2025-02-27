package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.ChatopUserDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;
import org.springframework.security.core.Authentication;

public interface ChatopUserService {
    AuthResponseDTO register(RegisterRequestDTO registerInfos);
    AuthResponseDTO login(LoginRequestDTO loginInfos);
    ChatopUserDTO getByAuthentication(Authentication authentication);
    ChatopUserDTO getById(Long id);
}
