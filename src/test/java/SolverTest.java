import loader.CsvLoader;
import loader.SudokuFileLoader;
import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.Solver;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {

    private Solver solver;

    @BeforeEach
    void before() {
        SudokuFileLoader sudokuFileLoader = new CsvLoader();
        Grid grid = new Grid(sudokuFileLoader.load("src/main/resources/simple/s1_sudoku.csv"));
        solver = new Solver(grid);
    }

    @Test
    public void isIllegalValueTest() {
        assertFalse(solver.isValueLegal(1,0, 9));
        assertFalse(solver.isValueLegal(1,0, 8));
        assertFalse(solver.isValueLegal(1,0, 4));
    }

    @Test
    public void isLegalValueTest() {
        assertTrue(solver.isValueLegal(3,1, 1));
        assertTrue(solver.isValueLegal(3,1, 8));
        assertTrue(solver.isValueLegal(3,1, 7));
    }

    @Test
    public void fillWithPossibilitiesTest() {
        solver.fillWithPossibilities();
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(4);
        expected.add(6);
        expected.add(7);
        int x = 0;
        int y = 0;
        Set<Integer> actual = solver.getGrid().getCell(x , y).getPossibilities();
        assertEquals(expected, actual);
    }
}
