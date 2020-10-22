package ee.icd0004.mavozd;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class WeatherReport {
    @JsonProperty("weatherReportDetails")
    private MainDetails mainDetails;

    @JsonProperty("currentWeatherReport")
    private CurrentWeather currentWeather;
}
