package fr.gharrowbm.occhatopbackend.repositories;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatopUserRepository extends JpaRepository<ChatopUser, Long> {
    Optional<ChatopUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
