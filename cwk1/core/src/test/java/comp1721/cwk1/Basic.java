// Correctness testing for COMP1721 Coursework 1 (Basic Solution)

package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class Basic {
  private static final double TOLERANCE = 0.000001;

  private LocalDateTime t1;
  private LocalDateTime t2;
  private LocalDateTime t3;

  private WeatherRecord rec1;
  private WeatherRecord rec2;
  private WeatherRecord rec3;

  private WeatherDataset dataset;

  @BeforeEach
  public void perTestSetup() {
    t1 = LocalDateTime.of(2015, 1, 2, 3, 0);
    t2 = LocalDateTime.of(2018, 4, 5, 6, 0);
    t3 = LocalDateTime.of(2018, 4, 5, 11, 0);

    rec1 = new WeatherRecord(t1, 2.4, 10.5, 102.1, 73.2);
    rec2 = new WeatherRecord(t2, 5.3, 12.9, 140.7, 36.8);
    rec3 = new WeatherRecord(t3, 4.9, 14.1, 163.2, 42.1);

    dataset = new WeatherDataset();
  }

  // WeatherRecord class

  @Test
  @DisplayName("Time field set up correctly")
  public void timeField() {
    assertAll(
      () -> assertThat(rec1.getTime(), is(t1)),
      () -> assertThat(rec2.getTime(), is(t2))
    );
  }

  @Test
  @DisplayName("Wind speed field set up correctly")
  public void windSpeedField() {
    assertAll(
      () -> assertThat(rec1.getWindSpeed(), closeTo(2.4, TOLERANCE)),
      () -> assertThat(rec2.getWindSpeed(), closeTo(5.3, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Temperature field set up correctly")
  public void temperatureField() {
    assertAll(
      () -> assertThat(rec1.getTemperature(), closeTo(10.5, TOLERANCE)),
      () -> assertThat(rec2.getTemperature(), closeTo(12.9, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Solar irradiance field set up correctly")
  public void solarIrradianceField() {
    assertAll(
      () -> assertThat(rec1.getSolarIrradiance(), closeTo(102.1, TOLERANCE)),
      () -> assertThat(rec2.getSolarIrradiance(), closeTo(140.7, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Humidity field set up correctly")
  public void humidityField() {
    assertAll(
      () -> assertThat(rec1.getHumidity(), closeTo(73.2, TOLERANCE)),
      () -> assertThat(rec2.getHumidity(), closeTo(36.8, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Correct string representation of a WeatherRecord")
  public void stringRepresentation() {
    assertAll(
      () -> assertThat(rec1.toString(), is("02/01/2015 03:00,2.4,10.5,102.1,73.2")),
      () -> assertThat(rec3.toString(), is("05/04/2018 11:00,4.9,14.1,163.2,42.1"))
    );
  }

  // WeatherDataset class

  @Test
  @DisplayName("Dataset size provided correctly")
  public void sizeOfDataset() {
    assertThat(dataset.size(), is(0));
    dataset.add(rec1);
    assertThat(dataset.size(), is(1));
  }

  @Test
  @DisplayName("WeatherRecord retrieved correctly")
  public void retrieveRecord() {
    dataset.add(rec1);
    WeatherRecord rec = dataset.get(0);
    assertAll(
      () -> assertThat(rec.getTime(), is(t1)),
      () -> assertThat(rec.getWindSpeed(), closeTo(2.4, TOLERANCE)),
      () -> assertThat(rec.getTemperature(), closeTo(10.5, TOLERANCE)),
      () -> assertThat(rec.getHumidity(), closeTo(73.2, TOLERANCE))
    );
  }

  @Test
  @DisplayName("FileNotFoundException if data file does not exist")
  public void nonExistentFile() {
    assertThrows(FileNotFoundException.class,
      () -> new WeatherDataset("file-that-does-not-exist"));
  }

  @Test
  @DisplayName("File with no data handled correctly")
  public void emptyFile() throws FileNotFoundException {
    WeatherDataset noRecords = new WeatherDataset("../datafiles/no-records.csv");
    assertThat(noRecords.size(), is(0));
  }

  @Test
  @DisplayName("Records with wrong field count ignored when reading file")
  public void notEnoughFields() throws FileNotFoundException {
    dataset.readFile("../datafiles/bad-records.csv");
    assertThat(dataset.size(), is(2));
  }

  @Test
  @DisplayName("Empty fields ignored when reading file")
  public void emptyFields() throws FileNotFoundException {
    dataset.readFile("../datafiles/empty-fields.csv");
    assertThat(dataset.size(), is(2));
  }

  @Test
  @DisplayName("File with data handled correctly")
  public void fileWithData() throws FileNotFoundException {
    WeatherDataset fourRecords = new WeatherDataset("../datafiles/four-records.csv");
    assertThat(fourRecords.size(), is(4));
  }

  @Test
  @DisplayName("readFile method reads data properly")
  public void readFile() throws FileNotFoundException {
    dataset.readFile("../datafiles/four-records.csv");
    assertThat(dataset.size(), is(4));
  }

  @Test
  @DisplayName("Existing data cleared when reading from new file")
  public void dataCleared() throws FileNotFoundException {
    dataset.readFile("../datafiles/four-records.csv");
    dataset.readFile("../datafiles/four-records.csv");
    assertThat(dataset.size(), is(4));
  }
}
