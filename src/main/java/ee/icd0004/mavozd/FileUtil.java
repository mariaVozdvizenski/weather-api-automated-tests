package ee.icd0004.mavozd;

import ee.icd0004.mavozd.model.WeatherReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static final String JSON_FORMAT = ".json";
    public static final String SUPPORTED_FORMAT = "txt";
    public static final File FOLDER = new File("generatedJson/");
    
    private static final Logger LOGGER = LogManager.getLogger(FileUtil.class.getName());

    public List<String> readCityNamesFromFile(String fileName) throws IOException {
        List<String> cities = null;
        if (fileExtensionIsCorrect(fileName)){
            try {
                cities = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                throw new IOException(String.format(("File %s doesn't exist!"), fileName));
            }
        } else {
            throw new IOException(String.format(("File extension is incorrect. It should be .%s"), SUPPORTED_FORMAT));
        }
        return cities;
    }

    private boolean fileExtensionIsCorrect(String filename){
        int dotIndex = filename.lastIndexOf('.');
        String extension = filename.substring(dotIndex + 1);
        return extension.equals("txt");
    }

    public void writeWeatherReportToFile(WeatherReport weatherReport) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(weatherReport);
        String city = weatherReport.getMainDetails().getCity();

        Path path = Paths.get("generatedJson/"+ city + JSON_FORMAT);
        byte[] strToBytes = jsonString.getBytes();

        if (fileAlreadyExistsInFolder(FOLDER, city)){
            LOGGER.info(String.format(("A weather report for %s is being overwritten."), city));
        }

        Files.write(path, strToBytes);
    }

    private boolean fileAlreadyExistsInFolder(final File folder, String cityName) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                fileAlreadyExistsInFolder(fileEntry, cityName);
            } else {
                if (fileEntry.getName().equals(cityName + JSON_FORMAT)){
                    return true;
                }
            }
        }
        return false;
    }

    public WeatherReport readWeatherReportFromFile(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> strings = Files.readAllLines(Paths.get(FOLDER.toString() + "/" + filename));
        return objectMapper.readValue(strings.get(0), WeatherReport.class);
    }
}
