package comp1721.cwk1;

/**
 * Class to represent errors in handling weather data.
 *
 * <p>Created for use in COMP1721 Coursework 1.</p>
 *
 * @author Nick Efford
 */
public class WeatherException extends RuntimeException {
  public WeatherException(String message) {
    super(message);
  }
}
