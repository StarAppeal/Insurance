package de.starappeal.versicherung.controller;

import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.services.UserDataService;
import de.starappeal.versicherung.services.VehicleTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleTypeController {

  private final VehicleTypeService vehicleTypeService;

  @Autowired
  public VehicleTypeController(
      VehicleTypeService vehicleTypeService, UserDataService userDataService) {
    this.vehicleTypeService = vehicleTypeService;
  }

  @ResponseBody
  @GetMapping
  public ResponseEntity<List<VehicleType>> getAllVehicleTypes() {
    List<VehicleType> vehicleTypes = vehicleTypeService.findAll();
    return ResponseEntity.ok().body(vehicleTypes);
  }

  @ResponseBody
  @GetMapping("{id}")
  public ResponseEntity<VehicleType> getVehicleTypeById(@PathVariable("id") Long id) {
    VehicleType vehicle = vehicleTypeService.findById(id);

    if (vehicle == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(vehicle);
  }
}
