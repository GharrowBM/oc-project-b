package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatopUserServiceImpl implements ChatopUserService {
    private final ChatopUserService chatopUserService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerInfos) {
        return null;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginInfos) {
        return null;
    }

    @Override
    public ChatopUser getById(Long id) {
        return null;
    }
}
