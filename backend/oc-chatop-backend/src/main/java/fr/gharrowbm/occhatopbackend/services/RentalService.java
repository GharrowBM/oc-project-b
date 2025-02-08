package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPostRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPutRequestDTO;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDTO> getAll();
    RentalDTO getById(Long id);
    BaseMessageResponse create(RentalPostRequestDTO rentalPostRequestDTO, String email);
    BaseMessageResponse update(Long id, RentalPutRequestDTO rentalPutRequestDTO, String email);
}
