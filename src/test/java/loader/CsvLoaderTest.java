package loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvLoaderTest {

    CsvLoader loader;

    @BeforeEach
    void setup(){

        this.loader = new CsvLoader();
    }

    @Test
    void test_PassWrongSizeFile(){

        assertNull(this.loader.load("src/test/test_resources/only_digits.csv"));
    }

    @Test
    void test_PassCsvFileWithWrongValues(){

        assertNull(this.loader.load("src/test/test_resources/wrong_signs.csv"));
    }

}