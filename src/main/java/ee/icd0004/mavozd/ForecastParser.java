package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.ForecastWeatherData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForecastParser {

    public ArrayList<ForecastReport> ParseForecastDataFromApi(ForecastWeatherData forecastWeatherData) {

        int counter = 0;
        int temp = 0;
        int pressure = 0;
        int humidity = 0;

        ArrayList<ForecastReport> forecastReports = new ArrayList<>();

        for (int i = 0; i < forecastWeatherData.getList().size(); i++) {

            temp += forecastWeatherData.getList().get(i).getMain().getTemp();
            pressure += forecastWeatherData.getList().get(i).getMain().getPressure();
            humidity += forecastWeatherData.getList().get(i).getMain().getPressure();
            counter++;

            if (counter == 8) {
                ForecastWeather forecastWeather = new ForecastWeather(temp/8, humidity/8, pressure/8);
                ForecastReport forecastReport = new ForecastReport();
                forecastReport.setWeather(forecastWeather);
                long unixTime = forecastWeatherData.getList().get(i).getDt();
                forecastReport.setDate(parseDate(unixTime));
                forecastReports.add(forecastReport);
                temp = 0;
                pressure = 0;
                humidity = 0;
                counter = 0;
            }
        }
        return forecastReports;
    }

    private String parseDate(long unix)
    {
        Date date = new java.util.Date(unix*1000);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}