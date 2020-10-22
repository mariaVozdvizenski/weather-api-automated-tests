package ee.icd0004.mavozd.api;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainForecast {
    private long dt;
    private MainForecastData main;
}
