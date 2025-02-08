package fr.gharrowbm.occhatopbackend.repositories;

import fr.gharrowbm.occhatopbackend.entities.ChatopMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatopMessageRepository extends JpaRepository<ChatopMessage, Long> {
}
