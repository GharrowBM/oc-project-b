package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;

public interface ChatopUserService {
    AuthResponseDTO register(RegisterRequestDTO registerInfos);
    AuthResponseDTO login(LoginRequestDTO loginInfos);
    ChatopUser getById(Long id);
}
