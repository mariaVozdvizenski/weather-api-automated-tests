package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.*;

import java.util.List;

public class WeatherTime {

    private final WeatherApi weatherApi;
    private final ForecastParser forecastParser;

    public WeatherTime(WeatherApi weatherApi, ForecastParser forecastParser) {
        this.weatherApi = weatherApi;
        this.forecastParser = forecastParser;
    }

    private CurrentWeatherData getCurrentWeatherData(String cityName){
        return weatherApi.getCurrentWeatherData(cityName);
    }

    public ForecastWeatherData getForecastWeatherData(String cityName){
        return weatherApi.getForecastWeatherData(cityName);
    }

    public WeatherReport getWeatherReportForCity(String cityName) {
        CurrentWeatherData currentWeatherData = getCurrentWeatherData(cityName);
        ForecastWeatherData forecastWeatherData = getForecastWeatherData(cityName);

        MainDetails mainDetails = createMainDetails(currentWeatherData);
        CurrentWeather currentWeather = createCurrentWeather(currentWeatherData);

        List<ForecastReport> forecastReports = forecastParser.parseForecastDataFromApi(forecastWeatherData);

        return createWeatherReport(mainDetails, currentWeather, forecastReports);
    }

    public WeatherReport createWeatherReport(MainDetails mainDetails, CurrentWeather currentWeather, List<ForecastReport> forecastReports) {
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setForecastReportList(forecastReports.subList(0, 3));
        weatherReport.setMainDetails(mainDetails);
        weatherReport.setCurrentWeather(currentWeather);
        return weatherReport;
    }


    private CurrentWeather createCurrentWeather(CurrentWeatherData currentWeatherData) {
        ForecastParser parser = new ForecastParser();
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setDate(parser.parseDate(currentWeatherData.getDt()));
        currentWeather.setTemperature(currentWeatherData.getMain().getTemp());
        currentWeather.setHumidity(currentWeatherData.getMain().getHumidity());
        currentWeather.setPressure(currentWeatherData.getMain().getPressure());
        return currentWeather;
    }

    private MainDetails createMainDetails(CurrentWeatherData currentWeatherData) {
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
