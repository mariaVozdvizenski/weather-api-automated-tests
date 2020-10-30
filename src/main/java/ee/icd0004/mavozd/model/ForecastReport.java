package ee.icd0004.mavozd.model;

import lombok.Data;

@Data
public class ForecastReport {
    private String date;
    private ForecastWeather weather;
}
