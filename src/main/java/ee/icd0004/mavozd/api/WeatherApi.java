package ee.icd0004.mavozd.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;

public class WeatherApi {

    private static final String BASEURL = "http://api.openweathermap.org/data/2.5";
    private static final String API_KEY = "f3ea571d8fac794ebdd079696b6d0ac4";
    private static final String UNITS = "metric";

    public CurrentWeatherData getCurrentWeatherData(String cityName) {
        Client client = getConfiguredClient();
        String resourceUrl = BASEURL + "/weather";

        ClientResponse response = client.resource(resourceUrl)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .get(ClientResponse.class);

        CurrentWeatherData currentWeatherData = response.getEntity(CurrentWeatherData.class);
        currentWeatherData.setTemperatureUnit("Celsius");

        return currentWeatherData;
    }

    public ForecastWeatherData getForecastWeatherData(String cityName) {
        Client client = getConfiguredClient();
        String resourceUrl = BASEURL + "/forecast";

        ClientResponse response = client.resource(resourceUrl)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .get(ClientResponse.class);

        return response.getEntity(ForecastWeatherData.class);
    }

    private static Client getConfiguredClient(){
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return Client.create(config);
    }
}
