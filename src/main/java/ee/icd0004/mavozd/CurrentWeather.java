package ee.icd0004.mavozd;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CurrentWeather {
    private String date = LocalDate.now().toString();
    private int temperature;
    private int humidity;
    private int pressure;
}
