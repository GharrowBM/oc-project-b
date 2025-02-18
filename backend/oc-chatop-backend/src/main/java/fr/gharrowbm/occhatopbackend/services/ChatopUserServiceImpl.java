package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.exceptions.ChatopUserNotFoundException;
import fr.gharrowbm.occhatopbackend.exceptions.UserEmailIsAlreadyTakenException;
import fr.gharrowbm.occhatopbackend.models.AuthResponseDTO;
import fr.gharrowbm.occhatopbackend.models.ChatopUserDTO;
import fr.gharrowbm.occhatopbackend.models.LoginRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RegisterRequestDTO;
import fr.gharrowbm.occhatopbackend.repositories.ChatopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatopUserServiceImpl implements ChatopUserService {
    private final ChatopUserRepository chatopUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerInfos) {
        if (chatopUserRepository.existsByEmail(registerInfos.email())) {
            throw new UserEmailIsAlreadyTakenException(registerInfos.email());
        }

        ChatopUser user = new ChatopUser();
        user.setEmail(registerInfos.email());
        user.setName(registerInfos.name());
        user.setPassword(passwordEncoder.encode(registerInfos.password()));
        chatopUserRepository.save(user);

        return new AuthResponseDTO(jwtService.generate(registerInfos.email()));
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginInfos) {
        ChatopUser foundUser = chatopUserRepository.findByEmail(loginInfos.email())
                .orElseThrow(() -> new ChatopUserNotFoundException("User with email [" + loginInfos.email() + "] not found"));

        if(!passwordEncoder.matches(loginInfos.password(), foundUser.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new AuthResponseDTO(jwtService.generate(loginInfos.email()));
    }

    @Override
    public ChatopUserDTO getByAuthentication(Authentication authentication) {
        String email = authentication.getName();
        ChatopUser foundUser = chatopUserRepository.findByEmail(email)
                .orElseThrow(() -> new ChatopUserNotFoundException("User with email [" + email + "] not found"));
        return new ChatopUserDTO(
                foundUser.getId(),
                foundUser.getEmail(),
                foundUser.getName(),
                foundUser.getCreatedAt().toString(),
                foundUser.getUpdatedAt().toString()
        );

    }

    @Override
    public ChatopUserDTO getById(Long id) {
        ChatopUser foundUser = chatopUserRepository.findById(id)
                .orElseThrow(() -> new ChatopUserNotFoundException("User with id [" + id + "] not found"));
        return new ChatopUserDTO(
                foundUser.getId(),
                foundUser.getEmail(),
                foundUser.getName(),
                foundUser.getCreatedAt().toString(),
                foundUser.getUpdatedAt().toString()
        );
    }


}
