package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.WeatherApi;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String cityName = "Tallinn";
        WeatherTime weatherTime = new WeatherTime(new WeatherApi());
        WeatherReport weatherReportForCity = weatherTime.getWeatherReportForCity(cityName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(weatherReportForCity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
