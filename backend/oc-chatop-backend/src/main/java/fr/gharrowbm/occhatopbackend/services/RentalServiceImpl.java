package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.entities.Rental;
import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPostRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPutRequestDTO;
import fr.gharrowbm.occhatopbackend.repositories.ChatopUserRepository;
import fr.gharrowbm.occhatopbackend.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final ChatopUserRepository chatopUserRepository;

    @Override
    public List<RentalDTO> getAll() {
        return rentalRepository.findAll()
                .stream()
                .map(this::fromEntity)
                .toList();
    }

    @Override
    public RentalDTO getById(Long id) {
        return rentalRepository.findById(id)
                .map(this::fromEntity)
                .orElse(null);
    }

    @Override
    public BaseMessageResponse create(RentalPostRequestDTO rentalPostRequestDTO, String email) {
        ChatopUser owner = chatopUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rental rental = new Rental();
        rental.setName(rentalPostRequestDTO.name());
        rental.setDescription(rentalPostRequestDTO.description());
        rental.setPrice(BigDecimal.valueOf(rentalPostRequestDTO.price()));
        rental.setSurface(rentalPostRequestDTO.surface());
        rental.setPictureUrl(rentalPostRequestDTO.picture().getName());
        rental.setOwner(owner);

        rentalRepository.save(rental);

        return new BaseMessageResponse("Rental created!");
    }

    @Override
    public BaseMessageResponse update(Long id, RentalPutRequestDTO rentalPutRequestDTO, String email) {
        ChatopUser owner = chatopUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));


        rental.setName(rentalPutRequestDTO.name());
        rental.setDescription(rentalPutRequestDTO.description());
        rental.setPrice(BigDecimal.valueOf(rentalPutRequestDTO.price()));
        rental.setSurface(rentalPutRequestDTO.surface());
        rental.setOwner(owner);

        rentalRepository.save(rental);

        return new BaseMessageResponse("Rental updated!");
    }

    private RentalDTO fromEntity(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice().floatValue(),
                rental.getPictureUrl(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt().toString(),
                rental.getUpdatedAt().toString()
        );
    }
}
