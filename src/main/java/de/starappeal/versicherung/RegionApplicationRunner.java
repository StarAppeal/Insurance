package de.starappeal.versicherung;

import de.starappeal.versicherung.entities.Region;
import de.starappeal.versicherung.services.RegionService;
import de.starappeal.versicherung.utils.CsvService;
import de.starappeal.versicherung.utils.DoubleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class RegionApplicationRunner implements ApplicationRunner {
  private static final String FILE_PATH = "src/main/resources/postcodes.csv";

  private final RegionService regionService;

  @Autowired
  public RegionApplicationRunner(RegionService regionService) {
    this.regionService = regionService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    CsvService csvService = new CsvService(FILE_PATH);

    csvService
        .readRows(CsvService.regionParser)
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
