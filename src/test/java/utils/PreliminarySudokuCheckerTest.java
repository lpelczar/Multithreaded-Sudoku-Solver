package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreliminarySudokuCheckerTest {

    private PreliminarySudokuChecker checker;

    @BeforeEach
    void setup(){
        this.checker = new PreliminarySudokuChecker();
    }

    @Test
    void test_nonrecurinValues(){
        int [] table = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertTrue(PreliminarySudokuChecker.isSudoku(table));
    }

    @Test
    void test_recurirngValuesInHorizonalRows(){

        int [] table = {2, 3, 2, 4, 2, 2, 7, 6, 8};
        assertFalse(PreliminarySudokuChecker.isSudoku(table));
    }

}