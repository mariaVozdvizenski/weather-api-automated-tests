package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.WeatherApi;
import ee.icd0004.mavozd.model.WeatherReport;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil();

        String cityName = fileUtil.readCityNameFromFile("cities.txt");
        WeatherTime weatherTime = new WeatherTime(new WeatherApi(), new ForecastParser());

        WeatherReport weatherReportForCity = weatherTime.getWeatherReportForCity(cityName);

        fileUtil.writeWeatherReportToFile(weatherReportForCity);
    }
}
