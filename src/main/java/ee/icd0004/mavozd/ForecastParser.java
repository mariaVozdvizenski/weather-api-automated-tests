package ee.icd0004.mavozd;

import ee.icd0004.mavozd.api.ForecastWeatherData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastParser {

    public List<ForecastReport> parseForecastDataFromApi(ForecastWeatherData forecastWeatherData) {

        int dayCounter = 0;
        int temp = 0;
        int pressure = 0;
        int humidity = 0;

        ArrayList<ForecastReport> forecastReports = new ArrayList<>();

        for (int i = 0; i < forecastWeatherData.getList().size(); i++) {

            temp += forecastWeatherData.getList().get(i).getMain().getTemp();
            pressure += forecastWeatherData.getList().get(i).getMain().getPressure();
            humidity += forecastWeatherData.getList().get(i).getMain().getPressure();
            dayCounter++;

            // Since there are 8 forecasts per day.
            if (dayCounter == 8) {
                ForecastWeather forecastWeather = new ForecastWeather(temp/8, humidity/8, pressure/8);
                ForecastReport forecastReport = new ForecastReport();
                forecastReport.setWeather(forecastWeather);
                long unixTime = forecastWeatherData.getList().get(i).getDt();
                forecastReport.setDate(parseDate(unixTime));
                forecastReports.add(forecastReport);
                temp = 0;
                pressure = 0;
                humidity = 0;
                dayCounter = 0;
            }
        }
        return forecastReports;
    }

    public String parseDate(long unix)
    {
        Date date = new java.util.Date(unix * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
