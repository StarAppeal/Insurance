package de.starappeal.versicherung.utils;

import static org.junit.jupiter.api.Assertions.*;

import de.starappeal.versicherung.utils.CsvService.RegionRow;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

class CsvServiceTest {

  private CsvService csvService;

  @BeforeEach
  void setup() {
    this.csvService = new CsvService("src/test/resources/test.csv");
  }

  @Test
  void readRows() {
    List<RegionRow> regions = csvService.readRows();
    RegionRow firstRegion = regions.get(0);

    assertEquals("Baden-Württemberg", firstRegion.state());
    assertEquals("Breisgau-Hochschwarzwald", firstRegion.country());
    assertEquals("Bad Krozingen", firstRegion.city());
    assertEquals("79189", firstRegion.zipCode());
    assertEquals("Bad Krozingen", firstRegion.location());

    System.out.println(regions);
  }
}
