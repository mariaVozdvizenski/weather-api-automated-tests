package ee.icd0004.mavozd.mock;

import ee.icd0004.mavozd.*;
import ee.icd0004.mavozd.api.*;
import ee.icd0004.mavozd.model.ForecastReport;
import ee.icd0004.mavozd.model.WeatherReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherTimeMockTests {

    @Mock
    static ForecastParser forecastParser;
    @Mock
    static WeatherApi weatherApi;

    @Test
    public void shouldHaveCorrectCityInCurrentWeatherData()
    {
        String cityName = "Tallinn";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setCoord(new Coordinates(0.0, 0.0));
        currentWeatherData.setMain(new MainWeatherData());

        currentWeatherData.setName(cityName);

        ForecastWeatherData mockedForecastWeatherData = mock(ForecastWeatherData.class);

        ForecastReport forecastReportMock = new ForecastReport();
        ArrayList<ForecastReport> forecastReports =
                new ArrayList<>(Arrays.asList(forecastReportMock, forecastReportMock, forecastReportMock));

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastWeatherData(anyString())).thenReturn(mockedForecastWeatherData);
        when(forecastParser.parseForecastDataFromApi(any(ForecastWeatherData.class))).thenReturn(forecastReports);

        WeatherTime weatherTime = new WeatherTime(weatherApi, forecastParser);

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getMainDetails().getCity()).isEqualTo(cityName);
    }

    @Test
    public void shouldHaveCorrectDateInForecastWeatherData()
    {
        String cityName = "Tallinn";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setCoord(new Coordinates(0.0, 0.0));
        currentWeatherData.setMain(new MainWeatherData());

        ForecastWeatherData forecastWeatherData = new ForecastWeatherData();

        Date dt = Date.from(Instant.now());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String actualDate = date.format(dt);

        ForecastReport forecastReportMock = new ForecastReport();
        forecastReportMock.setDate(actualDate);

        ArrayList<ForecastReport> forecastReports =
                new ArrayList<>(Arrays.asList(forecastReportMock, forecastReportMock, forecastReportMock));

        MainForecast mainForecast = new MainForecast();
        mainForecast.setDt(Instant.now().getEpochSecond());
        forecastWeatherData.addToList(mainForecast);

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastWeatherData(anyString())).thenReturn(forecastWeatherData);
        when(forecastParser.parseForecastDataFromApi(any(ForecastWeatherData.class))).thenReturn(forecastReports);

        WeatherTime weatherTime = new WeatherTime(weatherApi, forecastParser);

        WeatherReport weatherReport = weatherTime.getWeatherReportForCity(cityName);

        assertThat(weatherReport.getForecastReportList().get(0).getDate()).isEqualTo(actualDate);
    }
}
