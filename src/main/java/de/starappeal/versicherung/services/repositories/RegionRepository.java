package de.starappeal.versicherung.services.repositories;

import de.starappeal.versicherung.entities.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByZipCode(String zipCode);

}
