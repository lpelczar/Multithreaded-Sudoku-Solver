import loader.CsvLoader;
import loader.SudokuFileLoader;
import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.Solver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SolverTest {

    private Solver solver;

    @BeforeEach
    void before() {
        SudokuFileLoader sudokuFileLoader = new CsvLoader();
        Grid grid = new Grid(sudokuFileLoader.load("src/main/resources/simple/s1_sudoku.csv"));
        solver = new Solver(grid);
    }

    @Test
    public void isLegalValueTest() {
        assertFalse(solver.isValueLegal(1,0, 9));
        assertFalse(solver.isValueLegal(1,0, 8));
        assertFalse(solver.isValueLegal(1,0, 4));
    }
}
