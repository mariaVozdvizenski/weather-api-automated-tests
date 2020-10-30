package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.WeatherApi;
import ee.icd0004.mavozd.model.ForecastReport;
import ee.icd0004.mavozd.model.WeatherReport;
import org.apache.commons.validator.GenericValidator;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class WeatherTimeTests
{

    static WeatherTime weatherTime;

    @BeforeClass
    public static void setUp() {
        weatherTime = new WeatherTime(new WeatherApi(), new ForecastParser());
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

        Date dt = Date.from(Instant.now());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDate = date.format(dt);

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
    public void shouldHaveDatesInAscendingOrderInWeatherReport()
    {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        long dayCounter = 1L;
        for (ForecastReport forecastReport :weatherReport.getForecastReportList()) {
            String nextDay = LocalDate.now().plusDays(dayCounter).toString();
            assertThat(forecastReport.getDate()).isEqualTo(nextDay);
            dayCounter++;
        }
    }

    @Test
    public void shouldReturnEmptyJsonIfGivenCityNameThatDoesntExist() throws IOException {
        String cityName = "Tallinnz";

        ObjectMapper mapper = new ObjectMapper();

        WeatherReport actualWeatherReport = weatherTime.getWeatherReportForCity(cityName);
        String actualJson = mapper.writeValueAsString(actualWeatherReport);

        WeatherReport expectedWeatherReport = new WeatherReport();
        String expectedJson = mapper.writeValueAsString(expectedWeatherReport);

        assertThat(actualJson).isEqualTo(expectedJson);
    }


    @Test
    public void shouldBeAbleToWriteWeatherReportToFile() throws IOException {
        String cityName = "Tallinn";

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        FileUtil fileUtil = new FileUtil();
        fileUtil.writeWeatherReportToFile(weatherReport);

        assertThat(Files.readAllLines(Paths.get(FileUtil.JSON_FILENAME)).get(0)).isNotEmpty();
    }

    @Test
    public void shouldBeAbleToReadWeatherReportFromFile() throws IOException {
        FileUtil fileUtil = new FileUtil();
        WeatherReport weatherReport = fileUtil.readWeatherReportFromFile();
        assertThat(weatherReport).isNotNull();
    }

    @Test
    public void weatherReportJsonShouldEqualWeatherReportJsonFromFile() throws IOException {
        String cityName = "Tallinn";

        ObjectMapper objectMapper = new ObjectMapper();
        FileUtil fileUtil = new FileUtil();

        WeatherReport expectedWeatherReport = weatherTime.getWeatherReportForCity(cityName);

        fileUtil.writeWeatherReportToFile(expectedWeatherReport);

        WeatherReport actualWeatherReport = fileUtil.readWeatherReportFromFile();

        assertThat(objectMapper.writeValueAsString(expectedWeatherReport))
                .isEqualTo(objectMapper.writeValueAsString(actualWeatherReport));
    }
}
