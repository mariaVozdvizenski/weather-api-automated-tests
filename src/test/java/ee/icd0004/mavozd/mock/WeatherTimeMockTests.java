package ee.icd0004.mavozd.mock;

import ee.icd0004.mavozd.WeatherReport;
import ee.icd0004.mavozd.WeatherTime;
import ee.icd0004.mavozd.api.CurrentWeatherData;
import ee.icd0004.mavozd.api.WeatherApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherTimeMockTests {

    @Mock
    static WeatherApi weatherApi;

    @Test
    public void shouldHaveCorrectCityInWeatherReport()
    {
        String cityName = "Tallinn";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(cityName);

        WeatherTime weatherTime = new WeatherTime(weatherApi);

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getMainDetails().getCity()).isEqualTo(cityName);
    }
}
