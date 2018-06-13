package model;

import loader.CsvLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void test_passTableWithRepeatingValues(){

        int [] table = new CsvLoader().load("src/test/java/utils/repeatedValues.csv");
        assertThrows(IllegalArgumentException.class, () -> {
            new Grid(table);
        });


    }

}