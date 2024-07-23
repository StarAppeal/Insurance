package de.starappeal.versicherung;

import de.starappeal.versicherung.entities.Region;
import de.starappeal.versicherung.services.RegionService;
import de.starappeal.versicherung.utils.CsvService;
import de.starappeal.versicherung.utils.DoubleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class VersicherungApplication {

  public static void main(String[] args) {
    SpringApplication.run(VersicherungApplication.class, args);
  }


  // import csv file into the database, which is in memory
  @Component
  public static class CSVApplicationRunner implements ApplicationRunner {
      private static final String FILE_PATH = "src/main/resources/postcodes.csv";

    private final RegionService regionService;

    @Autowired
    public CSVApplicationRunner(RegionService regionService) {
      this.regionService = regionService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
      CsvService csvService = new CsvService(FILE_PATH);

      csvService
          .readRows()
          .forEach(
              regionRow -> {
                // its weird because there is no factor in the csv so we have to generate one random
                double factor = DoubleUtils.getRandomDouble(0.5d, 2.5d);

                Region region =
                    new Region(
                        regionRow.state(),
                        regionRow.country(),
                        regionRow.city(),
                        regionRow.zipCode(),
                        regionRow.location(),
                        factor);

                regionService.create(region);
              });
    }

  }
}
