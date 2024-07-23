package de.starappeal.versicherung.services;

import de.starappeal.versicherung.entities.Region;
import de.starappeal.versicherung.services.repositories.RegionRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService extends AbstractService {

  private static final Logger logger = LoggerFactory.getLogger(RegionService.class);

  private final RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    super(logger);
    this.regionRepository = regionRepository;
  }

  public Region findByZipCode(String zipCode) {
    logger.info("Got request to findByZipCode with zipCode={}", zipCode);
    return findWithExceptionHandling(
        () -> regionRepository.findByZipCode(zipCode),
        "Region could not be found with zipcode %s".formatted(zipCode));
  }

  public List<Region> findAll() {
    logger.info("Got request to findAll");
    return regionRepository.findAll();
  }

  // change it to location a different entity with the factor saved ?
  // welp, maybe its the best like it.. its in the definition
  public double getFactor(String zipCode) {
    return findByZipCode(zipCode).getFactor();
  }

  public void create(Region region) {
    logger.info("Got request to create a new region {}", region);

    regionRepository.save(region);
  }
}
