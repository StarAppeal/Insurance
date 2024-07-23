package de.starappeal.versicherung.services;

import de.starappeal.versicherung.entities.UserData;
import de.starappeal.versicherung.services.repositories.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

  private static final Logger logger = LoggerFactory.getLogger(UserDataService.class);
  private final UserDataRepository repository;

  @Autowired
  public UserDataService(UserDataRepository repository) {
    this.repository = repository;
  }

  public UserData save(UserData userData) {
    logger.info("Save user data: {}", userData);
    return repository.save(userData);
  }

  public UserData findById(Long id) {
    logger.info("Got request to findById with {}", id);
    return repository.findById(id).orElse(null);
  }
}
