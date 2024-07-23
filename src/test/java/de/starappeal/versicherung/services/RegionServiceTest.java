package de.starappeal.versicherung.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.starappeal.versicherung.entities.Region;
import de.starappeal.versicherung.services.repositories.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegionServiceTest {

  private RegionRepository repository;
  private RegionService service;

  @BeforeEach
  void setUp() {
    repository = mock(RegionRepository.class);
    service = new RegionService(repository);
  }

  @Test
  void findByZipCode() {
    Region region = new Region(1L, "state", "country", "city", "zipcode", "location", 1d);

    when(repository.findByZipCode(eq(region.getZipCode()))).thenReturn(Optional.of(region));

    assertEquals(region, service.findByZipCode(region.getZipCode()));
  }

  @Test
  void findByZipCode_Exception() {
    when(repository.findByZipCode(anyString())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.findByZipCode(anyString()));
  }

  @Test
  void findAll() {
    Region region1 = new Region(1L, "state", "country", "city", "zipcode", "location", 1d);
    Region region2 = new Region(2L, "state", "country", "city", "53757", "location", 2d);

    when(repository.findAll()).thenReturn(List.of(region1, region2));

    List<Region> regions = service.findAll();

    assertEquals(2, regions.size());

    assertEquals(region1, regions.get(0));
    assertEquals(region2, regions.get(1));
  }

  @Test
  void getFactor() {
    Region region = new Region(1L, "state", "country", "city", "zipcode", "location", 1d);

    when(repository.findByZipCode(eq(region.getZipCode()))).thenReturn(Optional.of(region));

    assertEquals(region.getFactor(), service.getFactor(region.getZipCode()));
  }

  @Test
  void save() {
    Region region = new Region(1L, "state", "country", "city", "zipcode", "location", 1d);

    service.save(region);

    verify(repository, times(1)).save(eq(region));
  }
}
