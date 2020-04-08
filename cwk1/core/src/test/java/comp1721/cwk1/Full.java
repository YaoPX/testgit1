// Correctness testing for COMP1721 Coursework 1 (Full Solution)

package comp1721.cwk1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class Full {
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

  @Test
  @DisplayName("Max wind speed found correctly")
  public void maxWindSpeed() {
    dataset.add(rec1);
    assertThat(dataset.maxWindSpeed(), is(rec1));
    dataset.add(rec2);
    dataset.add(rec3);
    assertThat(dataset.maxWindSpeed(), is(rec2));
  }

  @Test
  @DisplayName("Max temperature found correctly")
  public void maxTemperature() {
    dataset.add(rec1);
    assertThat(dataset.maxTemperature(), is(rec1));
    dataset.add(rec2);
    dataset.add(rec3);
    assertThat(dataset.maxTemperature(), is(rec3));
  }

  @Test
  @DisplayName("Min humidity found correctly")
  public void minHumidity() {
    dataset.add(rec1);
    assertThat(dataset.minHumidity(), is(rec1));
    dataset.add(rec2);
    dataset.add(rec3);
    assertThat(dataset.minHumidity(), is(rec2));
  }

  @Test
  @DisplayName("WeatherException if not enough data for analysis")
  public void notEnoughData() {
    assertAll(
      () -> assertThrows(WeatherException.class, () -> dataset.maxWindSpeed()),
      () -> assertThrows(WeatherException.class, () -> dataset.maxTemperature()),
      () -> assertThrows(WeatherException.class, () -> dataset.minHumidity())
    );
  }

  @Test
  @DisplayName("Insolation computed correctly")
  public void insolation() {
    dataset.add(rec1);
    dataset.add(rec2);
    dataset.add(rec3);
    assertAll(
      () -> assertThat(dataset.insolation(LocalDate.of(2015, 1, 2)), closeTo(102.1*3600, TOLERANCE)),
      () -> assertThat(dataset.insolation(LocalDate.of(2018, 4, 5)), closeTo(303.9*3600, TOLERANCE))
    );
  }

  @Test
  @DisplayName("WeatherException if insolation cannot be computed")
  public void wrongDateForInsolation() {
    dataset.add(rec1);
    dataset.add(rec2);
    LocalDate date = LocalDate.of(2015, 1, 1);
    assertThrows(WeatherException.class, () -> dataset.insolation(date));
  }
}
