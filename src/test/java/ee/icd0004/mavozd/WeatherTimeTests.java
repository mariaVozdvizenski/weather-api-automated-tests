package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.WeatherApi;
import org.apache.commons.validator.GenericValidator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTimeTests
{
    static WeatherTime weatherTime;

    @BeforeClass
    public static void setUp() {
        weatherTime = new WeatherTime(new WeatherApi());
    }

    @Test
    public void shouldHaveMainDetailsInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getMainDetails()).isNotNull();
    }

    @Test
    public void shouldHaveCorrectCityInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getMainDetails().getCity()).isEqualTo(cityName);
    }

    @Test
    public void shouldHaveCorrectCoordinatesInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        String coordinates = "59.44,24.75";

        assertThat(weatherReport.getMainDetails().getCoordinates()).isEqualTo(coordinates);
    }

    @Test
    public void shouldHaveCorrectTemperatureUnitInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        String expectedTemperatureUnit = "Celsius";

        assertThat(weatherReport.getMainDetails().getTemperatureUnit()).isEqualTo(expectedTemperatureUnit);
    }

    @Test
    public void shouldHaveCurrentWeatherInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getCurrentWeather()).isNotNull();
    }

    @Test
    public void shouldHaveCorrectDateInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        String expectedDate = LocalDate.now().toString();

        assertThat(weatherReport.getCurrentWeather().getDate()).isEqualTo(expectedDate);
    }

    @Test
    public void shouldHaveForecastReportListInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getForecastReportList());
    }

    @Test
    public void shouldHaveThreeDayForecastInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getForecastReportList()).hasSize(3);
    }

    @Test
    public void shouldHaveCorrectDateFormatInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        String actualDate = weatherReport.getForecastReportList().get(0).getDate();

        assertThat(GenericValidator.isDate(actualDate, "yyyy-mm-dd", true)).isTrue();
    }

    @Test
    public void shouldHaveCorrectDatesInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        String nextDay = LocalDate.now().plusDays(1L).toString();

        assertThat(weatherReport.getForecastReportList().get(0).getDate()).isEqualTo(nextDay);
    }
}
