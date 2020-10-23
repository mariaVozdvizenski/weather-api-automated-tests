package ee.icd0004.mavozd.api;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherData {
    private int temp;
    private int pressure;
    private int humidity;
}
