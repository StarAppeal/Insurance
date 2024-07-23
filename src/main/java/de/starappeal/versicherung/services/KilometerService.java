package de.starappeal.versicherung.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KilometerService {

  private static final Logger logger = LoggerFactory.getLogger(KilometerService.class);

  // there is no need for a database connection in my opinion

  public double getFactor(int kilometer) {
    logger.info("getFactor with kilometer: {}", kilometer);

    if (kilometer < 0) {
      throw new IllegalArgumentException("Kilometer must be greater than zero");
    }

    if (isBetween(kilometer, 0, 5000)) {
      return 0.5d;
    }

    if (isBetween(kilometer, 5001, 10000)) {
      return 1d;
    }

    if (isBetween(kilometer, 10001, 20000)) {
      return 1.5d;
    }

    return 2d;
  }

  // @VisibleForTesting
  boolean isBetween(int value, int min, int max) {
    return min <= value && value <= max;
  }
}
