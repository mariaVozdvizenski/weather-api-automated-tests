package ee.icd0004.mavozd;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FileUtilTests {

    @Test
    public void shouldBeAbleToReadCityNamesFromExistingFile() throws IOException {
        FileUtil fileUtil = new FileUtil();
        List<String> cityNames = fileUtil.readCityNamesFromFile("cities.txt");
        assertThat(cityNames.size()).isEqualTo(3);
    }

    @Test
    public void shouldThrowExceptionWithMessageIfFileDoesntExist(){
        FileUtil fileUtil = new FileUtil();
        String filename = "fake.txt";
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { fileUtil.readCityNamesFromFile(filename);})
                .withMessage("File %s doesn't exist!", filename)
                .withMessageContaining("exist")
                .withNoCause();
    }

    @Test
    public void shouldThrowExceptionWithMessageIfGiveWrongFileFormat(){
        FileUtil fileUtil = new FileUtil();
        String filename = "cities.csv";
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { fileUtil.readCityNamesFromFile(filename);})
                .withMessage("File extension is incorrect. It should be .%s", FileUtil.SUPPORTED_FORMAT)
                .withNoCause();
    }
}
