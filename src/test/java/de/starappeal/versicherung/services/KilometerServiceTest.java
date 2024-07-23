package de.starappeal.versicherung.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KilometerServiceTest {

  private final KilometerService kilometerService = new KilometerService();

  @Test
  void getFactor_Exception() {
    assertThrows(IllegalArgumentException.class, () -> kilometerService.getFactor(-1));
  }

  @Test
  void getFactor(){
    assertEquals(0.5d, kilometerService.getFactor(3500));
    assertEquals(0.5d, kilometerService.getFactor(5000));
    assertEquals(1d, kilometerService.getFactor(5001));
    assertEquals(1d, kilometerService.getFactor(9999));
    assertEquals(1d, kilometerService.getFactor(10000));
    assertEquals(1.5d, kilometerService.getFactor(10001));
    assertEquals(1.5d, kilometerService.getFactor(15312));
    assertEquals(1.5d, kilometerService.getFactor(20000));
    assertEquals(2d, kilometerService.getFactor(392392312));
}


  @Test
  void isBetween() {
    assertTrue(kilometerService.isBetween(1000, 500, 1500));
    assertTrue(kilometerService.isBetween(1000, 1000, 1001));
    assertTrue(kilometerService.isBetween(1000, 999, 1000));
    assertTrue(kilometerService.isBetween(1000, 999, 1001));
    assertFalse(kilometerService.isBetween(1000, 500, 999));
  }
}
