package de.starappeal.versicherung.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.services.repositories.VehicleTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTypeServiceTest {

  private VehicleTypeRepository repository;
  private VehicleTypeService service;

  @BeforeEach
  void setUp() {
    repository = mock(VehicleTypeRepository.class);
    service = new VehicleTypeService(repository);
  }

  @Test
  void save() {
    VehicleType vehicleType = new VehicleType(1L, "LKW", 12.1);

    service.save(vehicleType);

    verify(repository, times(1)).save(eq(vehicleType));
  }

  @Test
  void findById() {
    VehicleType vehicleType = new VehicleType(1L, "LKW", 12.1);
    when(repository.findById(eq(1L))).thenReturn(Optional.of(vehicleType));

    assertEquals(vehicleType, service.findById(1L));
  }

  @Test
  void find_ById_notFound() {
    when(repository.findById(eq(1L))).thenReturn(Optional.empty());

    assertNull(service.findById(1L));
  }

  @Test
  void findByIdByName() {
    VehicleType vehicleType = new VehicleType(1L, "LKW", 12.1);
    when(repository.findByName(eq("LKW"))).thenReturn(Optional.of(vehicleType));

    assertEquals(vehicleType, service.findByName("LKW"));
  }

  @Test
  void findByIdByName_Exception() {
    when(repository.findByName(eq("LKW"))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.findByName("LKW"));
  }

  @Test
  void findByIdAll() {
    VehicleType vehicleType1 = new VehicleType(1L, "LKW", 12.1);
    VehicleType vehicleType2 = new VehicleType(2L, "PKW", 0.3d);

    when(repository.findAll()).thenReturn(List.of(vehicleType1, vehicleType2));

    List<VehicleType> vehicleTypes = service.findAll();
    assertEquals(2, vehicleTypes.size());

    assertEquals(vehicleType1, vehicleTypes.get(0));
    assertEquals(vehicleType2, vehicleTypes.get(1));
  }

  @Test
  void getFactor() {
    VehicleType vehicleType = new VehicleType(1L, "LKW", 12.1);

    when(repository.findByName(eq("LKW"))).thenReturn(Optional.of(vehicleType));

    assertEquals(vehicleType.getFactor(), service.getFactor("LKW"));
  }
}
