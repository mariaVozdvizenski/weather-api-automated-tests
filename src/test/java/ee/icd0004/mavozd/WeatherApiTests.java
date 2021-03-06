package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.Coordinates;
import ee.icd0004.mavozd.api.CurrentWeatherData;
import ee.icd0004.mavozd.api.ForecastWeatherData;
import ee.icd0004.mavozd.api.WeatherApi;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

public class WeatherApiTests {

    @Test
    public void shouldReturnCorrectCityInCurrentWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setName(city);

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getName()).isEqualTo(expectedCurrentWeatherData.getName());
    }

    @Test
    public void shouldReturnCorrectTimestampInCurrentWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setDt(Instant.now().getEpochSecond());

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getDt()).isCloseTo(expectedCurrentWeatherData.getDt(), within(700L));
    }

    @Test
    public void shouldHaveCorrectCoordinatesInCurrentWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setCoord(new Coordinates(24.75, 59.44));

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getCoord()).isEqualTo(expectedCurrentWeatherData.getCoord());
    }

    @Test
    public void shouldHaveCorrectTemperatureUnitInCurrentWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setTemperatureUnit("Celsius");

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getTemperatureUnit()).isEqualTo(expectedCurrentWeatherData.getTemperatureUnit());
    }

    @Test
    public void shouldReturnCurrentWeatherDataEmptyObjectIfGivenCityThatDoesntExist() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinnz";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData).isEqualTo(expectedCurrentWeatherData);
    }

    @Test
    public void shouldReturnForecastWeatherDataEmptyObjectIfGivenCityThatDoesntExist() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn<";

        ForecastWeatherData expectedForecastWeatherData = new ForecastWeatherData();
        ForecastWeatherData forecastWeatherData = weatherApi.getForecastWeatherData(city);

        assertThat(forecastWeatherData).isEqualTo(expectedForecastWeatherData);
    }

    @Test
    public void shouldReturnFiveDayForecastInForecastWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        ForecastWeatherData forecastWeatherData = weatherApi.getForecastWeatherData(city);

        assertThat(forecastWeatherData.getList().size()).isEqualTo(40);
    }
}
