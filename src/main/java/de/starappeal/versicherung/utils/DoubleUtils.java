package de.starappeal.versicherung.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class DoubleUtils {

  private static final Random random = new Random();

  private DoubleUtils() {
    // no instantiating of util class
  }

  public static double getRandomDouble(double min, double max) {
    if (min > max) {
      throw new IllegalArgumentException("Max must be greater than min");
    }
    double randomValue = min + (max - min) * random.nextDouble();
    return round(randomValue, 1);
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
