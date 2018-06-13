package utils;

import loader.CsvLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuCheckerTest {

    private CsvLoader csvLoader = new CsvLoader();


    @Test
    void test_wrongValuesInSukodu(){
        int [] table = this.csvLoader.load("src/test/java/utils/repeatedValues.csv");
        assertFalse(SudokuChecker.isSudoku(table));
    }

    @Test
    void test_correctUnsolvedSudoku(){
        int [] table = this.csvLoader.load("src/main/resources/easy/e1_sudoku.csv");
        assertTrue(SudokuChecker.isSudoku(table));
    }

    @Test
    void test_correctSolvedSudoku(){
        int [] table = this.csvLoader.load("src/main/resources/easy/e1_sol.csv");
        assertTrue(SudokuChecker.isSudoku(table));

    }

}