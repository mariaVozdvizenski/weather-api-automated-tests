package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.*;

import java.util.List;

public class WeatherTime {

    private WeatherApi weatherApi;

    public WeatherTime(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public CurrentWeatherData getCurrentWeatherData(String cityName){
        return weatherApi.getCurrentWeatherData(cityName);
    }

    public ForecastWeatherData getForecastWeatherData(String cityName){
        return weatherApi.getForecastWeatherData(cityName);
    }

    public WeatherReport getWeatherReportForCity(String cityName) {
        ForecastParser forecastParser = new ForecastParser();
        WeatherReport weatherReport = new WeatherReport();

        CurrentWeatherData currentWeatherData = getCurrentWeatherData(cityName);
        ForecastWeatherData forecastWeatherData = getForecastWeatherData(cityName);

        MainDetails mainDetails = getMainDetails(currentWeatherData);
        CurrentWeather currentWeather = getCurrentWeather(currentWeatherData);

        List<ForecastReport> forecastReports = forecastParser.ParseForecastDataFromApi(forecastWeatherData);

        weatherReport.setForecastReportList(forecastReports.subList(0, 3));
        weatherReport.setMainDetails(mainDetails);
        weatherReport.setCurrentWeather(currentWeather);

        return weatherReport;
    }


    private CurrentWeather getCurrentWeather(CurrentWeatherData currentWeatherData) {
        ForecastParser parser = new ForecastParser();
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setDate(parser.parseDate(currentWeatherData.getDt()));
        currentWeather.setTemperature(currentWeatherData.getMain().getTemp());
        currentWeather.setHumidity(currentWeatherData.getMain().getHumidity());
        currentWeather.setPressure(currentWeatherData.getMain().getPressure());
        return currentWeather;
    }

    private MainDetails getMainDetails(CurrentWeatherData currentWeatherData) {
        MainDetails mainDetails = new MainDetails();
        mainDetails.setCity(currentWeatherData.getName());
        mainDetails.setCoordinates(parseCoordinates(currentWeatherData.getCoord()));
        mainDetails.setTemperatureUnit(currentWeatherData.getTemperatureUnit());
        return mainDetails;
    }

    private String parseCoordinates(Coordinates coordinates) {
        return String.format("%s,%s", coordinates.getLat(), coordinates.getLon());
    }
}
