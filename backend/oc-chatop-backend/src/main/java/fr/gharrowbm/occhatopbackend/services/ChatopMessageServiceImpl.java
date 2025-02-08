package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopMessage;
import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.entities.Rental;
import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.ChatopMessagePostRequestDTO;
import fr.gharrowbm.occhatopbackend.repositories.ChatopMessageRepository;
import fr.gharrowbm.occhatopbackend.repositories.ChatopUserRepository;
import fr.gharrowbm.occhatopbackend.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatopMessageServiceImpl implements ChatopMessageService {
    private final ChatopMessageRepository chatopMessageRepository;
    private final ChatopUserRepository chatopUserRepository;
    private final RentalRepository rentalRepository;

    @Override
    public BaseMessageResponse postMessage(ChatopMessagePostRequestDTO dto) {
        ChatopUser sender = chatopUserRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User with id [" + dto.userId() + "] not found"));

        Rental rental = rentalRepository.findById(dto.rentalId())
                .orElseThrow(() -> new RuntimeException("Rental with id [" + dto.rentalId() + "] not found"));

        ChatopMessage message = new ChatopMessage();
        message.setMessage(dto.message());
        message.setSender(sender);
        message.setRental(rental);

        chatopMessageRepository.save(message);

        return new BaseMessageResponse("Message send with success");
    }
}
