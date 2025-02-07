package fr.gharrowbm.occhatopbackend.config;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.repositories.ChatopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatopUserDetailsService implements UserDetailsService {
    private final ChatopUserRepository chatopUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatopUser foundUser = chatopUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email [" + username + "] not found"));

        return new User(foundUser.getEmail(), foundUser.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }
}
