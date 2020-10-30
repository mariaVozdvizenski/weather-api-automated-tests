package ee.icd0004.mavozd.model;

import lombok.Data;

import java.time.Instant;

@Data
public class CurrentWeather {
    private String date;
    private int temperature;
    private int humidity;
    private int pressure;
}
