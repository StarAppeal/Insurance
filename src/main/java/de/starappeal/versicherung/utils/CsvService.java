package de.starappeal.versicherung.utils;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvService {
  private static final char SEPARATOR = ',';

  public static final RowParser<RegionRow> regionParser =
      row -> {
        String country = row[0];
        String state = row[2];
        String location = row[4];
        String zip = row[6];
        String city = row[7];
        return new RegionRow(state, country, city, zip, location);
      };

  public static final RowParser<VehicleTypeRow> vehicleTypeParser =
      row -> new VehicleTypeRow(row[0]);

  private final String filepath;

  public CsvService(String filepath) {
    this.filepath = filepath;
  }

  public <T> List<T> readRows(RowParser<T> rowParser) {
    List<T> result = new ArrayList<>();

    try (CSVReader reader =
        new CSVReaderBuilder(new FileReader(filepath))
            .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
            .withSkipLines(1)
            .build()) {
      List<String[]> rows = reader.readAll();

      for (String[] row : rows) {
        result.add(rowParser.parse(row));
      }

      return result;

    } catch (IOException | CsvException e) {
      throw new RuntimeException(e);
    }
  }

  public record RegionRow(
      String state, String country, String city, String zipCode, String location) {}

  public record VehicleTypeRow(String vehicleName) {}

  @FunctionalInterface
  public interface RowParser<T> {
    T parse(String[] row) throws CsvException;
  }
}
