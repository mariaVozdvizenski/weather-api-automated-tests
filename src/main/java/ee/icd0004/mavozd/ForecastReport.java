package ee.icd0004.mavozd;

import lombok.Data;

@Data
public class ForecastReport {
    private String date;
    private ForecastWeather weather;
}
