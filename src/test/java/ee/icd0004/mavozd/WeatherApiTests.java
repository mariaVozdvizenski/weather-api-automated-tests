package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.Coordinates;
import ee.icd0004.mavozd.api.CurrentWeatherData;
import ee.icd0004.mavozd.api.WeatherApi;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

public class WeatherApiTests {

    @Test
    public void shouldReturnCorrectCityInApiWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setName(city);

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getName()).isEqualTo(expectedCurrentWeatherData.getName());
    }

    @Test
    public void shouldReturnCorrectTimestampInApiWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setDt(Instant.now().getEpochSecond());

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getDt()).isCloseTo(expectedCurrentWeatherData.getDt(), within(700L));
    }

    @Test
    public void shouldHaveCorrectCoordinatesInApiWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setCoord(new Coordinates(24.75, 59.44));

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getCoord()).isEqualTo(expectedCurrentWeatherData.getCoord());
    }

    @Test
    public void shouldHaveCorrectTemperatureUnitInApiWeatherData() {
        WeatherApi weatherApi = new WeatherApi();

        String city = "Tallinn";

        CurrentWeatherData expectedCurrentWeatherData = new CurrentWeatherData();
        expectedCurrentWeatherData.setTemperatureUnit("Celsius");

        CurrentWeatherData actualCurrentWeatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(actualCurrentWeatherData.getTemperatureUnit()).isEqualTo(expectedCurrentWeatherData.getTemperatureUnit());
    }

}
