package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDTO> getAll();
    RentalDTO getById(Long id);
    BaseMessageResponse create();
    BaseMessageResponse update(Long id);
}
