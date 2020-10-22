package ee.icd0004.mavozd;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherReport {
    @JsonProperty("weatherReportDetails")
    private MainDetails mainDetails;

    @JsonProperty("currentWeatherReport")
    private CurrentWeather currentWeather;

    @JsonProperty("forecastReport")
    private List<ForecastReport> forecastReportList;

    public void addToForecastReportList(ForecastReport forecastReport){
        forecastReportList.add(forecastReport);
    }
}
