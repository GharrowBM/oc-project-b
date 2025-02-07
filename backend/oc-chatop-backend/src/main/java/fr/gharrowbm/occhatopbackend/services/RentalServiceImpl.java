package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.Rental;
import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;
import fr.gharrowbm.occhatopbackend.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

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
    public BaseMessageResponse create() {
        return new BaseMessageResponse("Rental created!");
    }

    @Override
    public BaseMessageResponse update(Long id) {
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
