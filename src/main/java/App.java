import loader.CsvLoader;
import loader.SudokuFileLoader;
import model.Grid;
import solver.Solver;
import solver.SolverThread;

public class App {

    public static void main(String[] args) {

        if (args.length == 1) {
            String filename = args[0];
            SudokuFileLoader sudokuFileLoader = new CsvLoader();
            int[] sudokuArray = sudokuFileLoader.load(filename);
            if (sudokuArray != null) {
                Grid grid = new Grid(sudokuArray);
                Solver solver = new Solver(grid);
                Thread thread = new Thread(new SolverThread(solver));
                thread.start();
            } else {
                System.out.println("Invalid sudoku file!");
            }
        } else {
            System.out.println("You need to specify sudoku filename as console argument!");
        }
    }
}
