package de.starappeal.versicherung.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoubleUtilsTest {

  @Test
  void getRandomDouble() {

    // how to test random functionality ...
    // in a util class probably not..?
    // maybe mocking Random, but it wouldn't be static anymore.. Yikes.

    double minValue = 0.5;
    double maxValue = 3.8;
    for (int i = 0; i < 100; i++) {
      double randomDouble = DoubleUtils.getRandomDouble(0.5, 1.3d);
      System.out.println(randomDouble);
      assertTrue(randomDouble >= minValue && randomDouble <= maxValue);
    }
  }

  @Test
  void getRandomDouble_Exception() {
    assertThrows(IllegalArgumentException.class, () -> DoubleUtils.getRandomDouble(1.3d, 0.5d));

  }

  @Test
  void round() {
    double toRound = 123.2232121320d;

    assertEquals(123.2, DoubleUtils.round(toRound, 1), 0);
    assertEquals(123.22, DoubleUtils.round(toRound, 2), 0);
    assertEquals(123.223, DoubleUtils.round(toRound, 3), 0);
  }
}
