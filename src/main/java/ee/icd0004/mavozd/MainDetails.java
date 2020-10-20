package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.Coordinates;
import lombok.Data;

@Data
public class MainDetails {
    private String city;
    private Coordinates coordinates;
    private String temperatureUnit;
}
