package de.starappeal.versicherung.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.slf4j.Logger;

public abstract class AbstractService {

  private final Logger logger;

  public AbstractService(Logger logger) {
    this.logger = logger;
  }

  public <T> T findWithExceptionHandling(Delegate<T> delegate, String errorMessage) {
    return delegate
        .delegate()
        .orElseThrow(
            () -> {
              logger.error(errorMessage);
              return new EntityNotFoundException(errorMessage);
            });
  }

  @FunctionalInterface
  public interface Delegate<T> {
    Optional<T> delegate();
  }
}
