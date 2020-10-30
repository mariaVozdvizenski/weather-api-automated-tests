package ee.icd0004.mavozd.model;

import ee.icd0004.mavozd.model.CurrentWeather;
import ee.icd0004.mavozd.model.ForecastReport;
import ee.icd0004.mavozd.model.MainDetails;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

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
