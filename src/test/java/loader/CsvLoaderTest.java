package loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CsvLoaderTest {

    CsvLoader loader;

    @BeforeEach
    void setup(){
        this.loader = new CsvLoader();
    }


    @Test
    void testPassWrongSizeFile(){

        assertNull(this.loader.load("src/test/test_resources/only_digits.csv"));
    }
    



}