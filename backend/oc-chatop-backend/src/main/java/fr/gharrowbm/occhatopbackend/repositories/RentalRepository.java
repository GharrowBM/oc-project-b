package fr.gharrowbm.occhatopbackend.repositories;

import fr.gharrowbm.occhatopbackend.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
