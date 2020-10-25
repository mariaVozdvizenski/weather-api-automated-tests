package ee.icd0004.mavozd.api;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
    private long dt;
    private Coordinates coord;
    private String temperatureUnit;
    private MainWeatherData main;
}
