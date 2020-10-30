package ee.icd0004.mavozd;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FileUtilTests {

    @Test
    public void shouldBeAbleToReadCityNameFromExistingFile() throws IOException {
        FileUtil fileUtil = new FileUtil();
        String cityName = fileUtil.readCityNameFromFile("cities.txt");
        assertThat(cityName).isEqualTo("Tallinn");
    }

    @Test
    public void shouldThrowExceptionWithMessageIfFileDoesntExist(){
        FileUtil fileUtil = new FileUtil();
        String filename = "fake.txt";
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { fileUtil.readCityNameFromFile(filename);})
                .withMessage("File %s doesn't exist!", filename)
                .withMessageContaining("exist")
                .withNoCause();
    }

    @Test
    public void shouldThrowExceptionWithMessageIfGiveWrongFileFormat(){
        FileUtil fileUtil = new FileUtil();
        String filename = "cities.csv";
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { fileUtil.readCityNameFromFile(filename);})
                .withMessage("File extension is incorrect. It should be .%s", FileUtil.SUPPORTED_FORMAT)
                .withNoCause();
    }
}
