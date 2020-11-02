package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.*;
import ee.icd0004.mavozd.model.CurrentWeather;
import ee.icd0004.mavozd.model.ForecastReport;
import ee.icd0004.mavozd.model.MainDetails;
import ee.icd0004.mavozd.model.WeatherReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class WeatherTime {

    private final WeatherApi weatherApi;
    private final ForecastParser forecastParser;
    private final FileUtil fileUtil;
    private static final Logger logger = LogManager.getLogger(WeatherTime.class.getName());

    public WeatherTime(WeatherApi weatherApi, ForecastParser forecastParser, FileUtil fileUtil) {
        this.weatherApi = weatherApi;
        this.forecastParser = forecastParser;
        this.fileUtil = fileUtil;
    }

    private CurrentWeatherData getCurrentWeatherData(String cityName){
        return weatherApi.getCurrentWeatherData(cityName);
    }

    public ForecastWeatherData getForecastWeatherData(String cityName){
        return weatherApi.getForecastWeatherData(cityName);
    }

    public void writeWeatherReportsToFile(String fileWithCityNames) throws IOException {
        List<String> cityNames = fileUtil.readCityNamesFromFile(fileWithCityNames);
        for (String name: cityNames) {
            WeatherReport weatherReportForCity = getWeatherReportForCity(name);
            if (weatherReportForCity.getMainDetails() != null){
                fileUtil.writeWeatherReportToFile(weatherReportForCity);
            } else {
                logger.error(String.format(("Incorrect city: %s"), name));
            }
        }
    }

    public WeatherReport getWeatherReportForCity(String cityName) {
        CurrentWeatherData currentWeatherData = getCurrentWeatherData(cityName);
        ForecastWeatherData forecastWeatherData = getForecastWeatherData(cityName);

        if (correctResponseWasReceived(currentWeatherData)) {
            MainDetails mainDetails = createMainDetails(currentWeatherData);
            CurrentWeather currentWeather = createCurrentWeather(currentWeatherData);
            List<ForecastReport> forecastReports = forecastParser.parseForecastDataFromApi(forecastWeatherData);
            return createWeatherReport(mainDetails, currentWeather, forecastReports);
        }
        return new WeatherReport();
    }

    private WeatherReport createWeatherReport(MainDetails mainDetails, CurrentWeather currentWeather, List<ForecastReport> forecastReports) {
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

    private boolean correctResponseWasReceived(CurrentWeatherData currentWeatherData) {
        return currentWeatherData.getName() != null;
    }

    private String parseCoordinates(Coordinates coordinates) {
        return String.format("%s,%s", coordinates.getLat(), coordinates.getLon());
    }
}
