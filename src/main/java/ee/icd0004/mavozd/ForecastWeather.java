package ee.icd0004.mavozd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastWeather {
    private int temperature;
    private int humidity;
    private int pressure;
}
