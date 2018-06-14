package model;

import loader.CsvLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void test_passTableWithRepeatingValues(){

        int [] table = new CsvLoader().load("src/test/test_resources/repeatedValues.csv");
        assertThrows(IllegalArgumentException.class, () -> {
            new Grid(table);
        });


    }

    @Test
    void test_load(){
        int [] table = new CsvLoader().load("src/main/resources/easy/e1_sudoku.csv");
        assertEquals(new Grid(table).toStringForTest(), Arrays.toString(table));
    }

}