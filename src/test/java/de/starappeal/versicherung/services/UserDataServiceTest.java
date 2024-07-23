package de.starappeal.versicherung.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.starappeal.versicherung.entities.UserData;
import de.starappeal.versicherung.services.repositories.UserDataRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDataServiceTest {

  private UserDataRepository userDataRepository;
  private UserDataService userDataService;

  @BeforeEach
  void setUp() {
    userDataRepository = mock(UserDataRepository.class);
    userDataService = new UserDataService(userDataRepository);
  }

  @Test
  void save() {
    UserData userData = new UserData(1L, 1000, "LKW", "53757", 3.2d);

    userDataService.save(userData);

    verify(userDataRepository, times(1)).save(eq(userData));
  }

  @Test
  void findById() {
    UserData userData = new UserData(1L, 1000, "LKW", "53757", 3.2d);

    when(userDataRepository.findById(eq(userData.getId()))).thenReturn(Optional.of(userData));

    assertEquals(userData, userDataService.findById(userData.getId()));
  }
}
