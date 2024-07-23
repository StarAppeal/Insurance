package de.starappeal.versicherung.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceCalculationServiceTest {

  private KilometerService kilometerService;
  private RegionService regionService;
  private VehicleTypeService vehicleTypeService;
  private InsuranceCalculationService insuranceCalculationService;

  @BeforeEach
  public void setup() {
    this.kilometerService = mock(KilometerService.class);
    this.regionService = mock(RegionService.class);
    this.vehicleTypeService = mock(VehicleTypeService.class);

    insuranceCalculationService =
        new InsuranceCalculationService(kilometerService, regionService, vehicleTypeService);
  }

  @Test
  void calculateInsurance() {
    double kilometerFactor = 1d;
    double regionFactor = 3.2d;
    double vehicleFactor = 2.2d;
    when(kilometerService.getFactor(anyInt())).thenReturn(kilometerFactor);
    when(regionService.getFactor(anyString())).thenReturn(regionFactor);
    when(vehicleTypeService.getFactor(anyString())).thenReturn(vehicleFactor);

    assertEquals(
        kilometerFactor * regionFactor * vehicleFactor,
        insuranceCalculationService.calculateInsurance(1337, "53757", "PKW"));
  }
}
