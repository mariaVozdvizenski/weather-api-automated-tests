package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.CurrentWeatherData;
import ee.icd0004.mavozd.api.WeatherApi;

public class WeatherTime {

    private WeatherApi weatherApi;

    public WeatherTime(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReportForCity(String cityName) {
        WeatherReport weatherReport = new WeatherReport();

        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(cityName);

        MainDetails mainDetails = new MainDetails();
        mainDetails.setCity(currentWeatherData.getName());
        mainDetails.setCoordinates(currentWeatherData.getCoord());
        mainDetails.setTemperatureUnit(currentWeatherData.getTemperatureUnit());

        weatherReport.setMainDetails(mainDetails);

        return weatherReport;
    }
}
