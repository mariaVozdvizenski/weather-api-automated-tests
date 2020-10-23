package ee.icd0004.mavozd.api;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeatherData {

    private List<MainForecast> list = new ArrayList<>();

    public void addToList(MainForecast mainForecast){
        list.add(mainForecast);
    }
}

