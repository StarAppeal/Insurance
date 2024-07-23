package de.starappeal.versicherung.services;

import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.services.repositories.VehicleTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeService extends AbstractService {

  private static final Logger logger = LoggerFactory.getLogger(VehicleTypeService.class);

  private final VehicleTypeRepository repository;

  @Autowired
  public VehicleTypeService(VehicleTypeRepository repository) {
    super(logger);
    this.repository = repository;
  }

  public VehicleType create(VehicleType vehicleType) {
    return repository.save(vehicleType);
  }

  public VehicleType find(Long id) {
    logger.info("Got request to find vehicle type with id: {}", id);
    return repository.findById(id).orElse(null);
  }

  public VehicleType findByName(String name) {
    logger.info("Got request to find vehicle type with name: {}", name);

    return findWithExceptionHandling(
        () -> repository.findByName(name), "Vehicle with name %s was not found".formatted(name));
  }

  public List<VehicleType> findAll() {
    logger.info("Got request to find all vehicle types");
    return repository.findAll();
  }

  public double getFactor(String vehicleName) {
    return findByName(vehicleName).factor();
  }
}
