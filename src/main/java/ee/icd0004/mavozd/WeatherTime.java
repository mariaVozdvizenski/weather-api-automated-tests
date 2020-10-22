package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.Coordinates;
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
        mainDetails.setCoordinates(parseCoordinates(currentWeatherData.getCoord()));
        mainDetails.setTemperatureUnit(currentWeatherData.getTemperatureUnit());

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemperature(currentWeatherData.getMain().getTemp());
        currentWeather.setHumidity(currentWeatherData.getMain().getHumidity());
        currentWeather.setPressure(currentWeatherData.getMain().getPressure());


        weatherReport.setMainDetails(mainDetails);
        weatherReport.setCurrentWeather(currentWeather);

        return weatherReport;
    }

    private String parseCoordinates(Coordinates coordinates) {
        return String.format("%s,%s", coordinates.getLat(), coordinates.getLon());
    }
}
