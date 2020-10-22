package ee.icd0004.mavozd.api;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDataMain {
    private int temp;
    private int humidity;
    private int pressure;
}
