package ee.icd0004.mavozd;

import ee.icd0004.mavozd.model.WeatherReport;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static final String JSON_FILENAME = "WeatherReport.json";
    public static final String SUPPORTED_FORMAT = "txt";

    public String readCityNameFromFile(String fileName) throws IOException {
        List<String> strings = null;
        if (fileExtensionIsCorrect(fileName)){
            try {
                strings = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                throw new IOException(String.format(("File %s doesn't exist!"), fileName));
            }
        } else {
            throw new IOException(String.format(("File extension is incorrect. It should be .%s"), SUPPORTED_FORMAT));
        }
        return strings.get(0);
    }

    private boolean fileExtensionIsCorrect(String filename){
        int dotIndex = filename.lastIndexOf('.');
        String extension = filename.substring(dotIndex + 1);
        return extension.equals("txt");
    }

    public void writeWeatherReportToFile(WeatherReport weatherReport) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(weatherReport);

        Path path = Paths.get(JSON_FILENAME);
        byte[] strToBytes = jsonString.getBytes();

        Files.write(path, strToBytes);
    }

    public WeatherReport readWeatherReportFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> strings = Files.readAllLines(Paths.get(FileUtil.JSON_FILENAME));
        return objectMapper.readValue(strings.get(0), WeatherReport.class);
    }
}
