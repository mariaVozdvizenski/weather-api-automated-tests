package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.WeatherApi;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WeatherTime weatherTime = new WeatherTime(new WeatherApi(), new ForecastParser(), new FileUtil());
        weatherTime.writeWeatherReportsToFile("cities.txt");
    }
}
