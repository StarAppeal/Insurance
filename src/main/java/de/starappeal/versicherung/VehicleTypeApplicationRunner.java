package de.starappeal.versicherung;

import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.services.VehicleTypeService;
import de.starappeal.versicherung.utils.CsvService;
import de.starappeal.versicherung.utils.DoubleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class VehicleTypeApplicationRunner implements ApplicationRunner {

  private static final String FILE_PATH = "src/main/resources/vehicleTypes.csv";

  private final VehicleTypeService vehicleTypeService;

  @Autowired
  public VehicleTypeApplicationRunner(VehicleTypeService vehicleTypeService) {
    this.vehicleTypeService = vehicleTypeService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    CsvService csvService = new CsvService(FILE_PATH);

    csvService
        .readRows(CsvService.vehicleTypeParser)
        .forEach(
            vehicleType -> {
              double factor = DoubleUtils.getRandomDouble(0.8, 2.0);
              vehicleTypeService.save(new VehicleType(null, vehicleType.vehicleName(), factor));
            });
  }
}
