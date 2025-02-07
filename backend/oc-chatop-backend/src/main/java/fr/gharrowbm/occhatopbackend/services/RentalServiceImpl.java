package fr.gharrowbm.occhatopbackend.services;

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
        return null;
    }

    @Override
    public RentalDTO getById(Long id) {
        return null;
    }

    @Override
    public BaseMessageResponse create() {
        return null;
    }

    @Override
    public BaseMessageResponse update(Long id) {
        return null;
    }
}
