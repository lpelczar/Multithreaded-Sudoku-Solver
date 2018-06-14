import loader.CsvLoader;
import loader.SudokuFileLoader;
import model.Grid;
import model.Menu;
import solver.Solver;
import solver.SolverThread;
import utils.InvalidSudokuException;

public class App {

    public static void main(String[] args) {

        if (args.length == 0) {
            new Menu().run();
        }

        if (args.length == 1) {
            String filename = args[0];
            SudokuFileLoader sudokuFileLoader = new CsvLoader();
            int[] sudokuArray = sudokuFileLoader.load(filename);
            if (sudokuArray != null) {
                Grid grid = null;
                try {
                    grid = new Grid(sudokuArray);
                    Solver solver = new Solver(grid);
                    Thread thread = new Thread(new SolverThread(solver));
                    thread.start();
                } catch (InvalidSudokuException e) {
                    System.out.println("Invalid sudoku");
                }
            } else {
                System.out.println("Invalid sudoku file!");
            }
        }
    }
}
