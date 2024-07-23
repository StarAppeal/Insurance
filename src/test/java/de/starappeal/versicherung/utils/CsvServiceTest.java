package de.starappeal.versicherung.utils;

import static org.junit.jupiter.api.Assertions.*;

import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.utils.CsvService.RegionRow;
import de.starappeal.versicherung.utils.CsvService.VehicleTypeRow;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

class CsvServiceTest {

  private CsvService csvService;

  @Test
  void readRows_Region() {
    this.csvService = new CsvService("src/test/resources/regions.csv");
    List<RegionRow> regions = csvService.readRows(CsvService.regionParser);
    assertEquals(7, regions.size());

    RegionRow firstRegion = regions.get(0);

    assertEquals("Baden-Württemberg", firstRegion.state());
    assertEquals("DE", firstRegion.country());
    assertEquals("Bad Krozingen", firstRegion.city());
    assertEquals("79189", firstRegion.zipCode());
    assertEquals("Breisgau-Hochschwarzwald", firstRegion.location());

    System.out.println(regions);
  }

  @Test
  void readRows_VehicleType() {
    this.csvService = new CsvService("src/test/resources/vehicles.csv");

    List<VehicleTypeRow> vehicleTypes = csvService.readRows(CsvService.vehicleTypeParser);

    assertEquals(2, vehicleTypes.size());

    VehicleTypeRow firstVehicleType = vehicleTypes.get(0);

    assertEquals("Test", firstVehicleType.vehicleName());

  }
}
