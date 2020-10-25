package ee.icd0004.mavozd.mock;

import ee.icd0004.mavozd.WeatherTime;
import ee.icd0004.mavozd.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherTimeMockTests {

    @Mock
    static WeatherApi weatherApi;

    @Test
    public void shouldHaveCorrectCityInCurrentWeatherData()
    {
        String cityName = "Tallinn";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(cityName);
        currentWeatherData.setTemperatureUnit("Celsius");
        currentWeatherData.setCoord(new Coordinates(0.0, 0.0));

        WeatherTime weatherTime = new WeatherTime(weatherApi);

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);

        CurrentWeatherData weatherReport = weatherTime.getCurrentWeatherData(cityName);

        assertThat(weatherReport.getName()).isEqualTo(cityName);
    }

    @Test
    public void shouldHaveCorrectDateInForecastWeatherData()
    {
        String cityName = "Tallinn";

        ForecastWeatherData forecastWeatherData = new ForecastWeatherData();

        MainForecast mainForecast = new MainForecast();
        mainForecast.setDt(Instant.now().getEpochSecond());

        forecastWeatherData.addToList(mainForecast);

        WeatherTime weatherTime = new WeatherTime(weatherApi);

        when(weatherApi.getForecastWeatherData(anyString())).thenReturn(forecastWeatherData);

        ForecastWeatherData weatherReport = weatherTime.getForecastWeatherData(cityName);

        assertThat(weatherReport.getList().get(0).getDt()).isEqualTo(Instant.now().getEpochSecond());
    }
}
