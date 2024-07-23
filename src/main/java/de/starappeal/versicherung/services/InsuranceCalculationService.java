package de.starappeal.versicherung.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceCalculationService {

  private static final Logger logger = LoggerFactory.getLogger(InsuranceCalculationService.class);

  private final KilometerService kilometerService;
  private final RegionService regionService;
  private final VehicleTypeService vehicleTypeService;

  @Autowired
  public InsuranceCalculationService(
      KilometerService kilometerService,
      RegionService regionService,
      VehicleTypeService vehicleTypeService) {
    this.kilometerService = kilometerService;
    this.regionService = regionService;
    this.vehicleTypeService = vehicleTypeService;
  }

  public double calculateInsurance(int kilometer, String zipCode, String vehicleName) {
    logger.info("Insurance calculation started");

    double kilometerFactor = kilometerService.getFactor(kilometer);
    double regionFactor = regionService.getFactor(zipCode);
    double vehicleFactor = vehicleTypeService.getFactor(vehicleName);

    logger.debug("kilometerFactor: {}", kilometerFactor);
    logger.debug("regionFactor: {}", regionFactor);
    logger.debug("vehicleFactor: {}", vehicleFactor);

    return kilometerFactor * regionFactor * vehicleFactor;
  }
}
