package solver;


import model.Cell;
import model.Grid;
import model.SolutionListener;
import utils.InvalidSudokuException;


public class SolverThread implements Runnable {

    private static int threadsUsed = 0;
    private Solver solver;
    private static SolutionListener listener;

    public SolverThread(Solver solver) {
        this.solver = solver;
    }

    public void registerListener(SolutionListener solutionListener) {
        listener = solutionListener;
    }

    private void notifySolution(int[] solution) {
        listener.solutionFound(solution, threadsUsed);
    }

    @Override
    public void run() {

        threadsUsed++;

        boolean isSolved = solver.solve();

        if (isSolved) {
            notifySolution(solver.getGrid().translateCells());
            System.out.println(solver.getGrid().toString());
            System.out.println("Threads used: " + threadsUsed);
        } else {
            try {
                splitSolving();
            } catch (InvalidSudokuException e) {
                // invalid sudoku
            }
        }
    }

    private void splitSolving() {


        Cell cell = getCellWithLowestPossibilities();
        int x = cell.getX();
        int y = cell.getY();
        int valueA = (int) cell.getPossibilities().toArray()[0];
        int valueB = (int) cell.getPossibilities().toArray()[1];


        int[] cellValues = solver.getGrid().translateCells();

            // TODO: create a method to not repeat code
            Grid gridA = new Grid(cellValues);
            gridA.getCell(x, y).setValue(valueA);
            Solver solverA = new Solver(gridA);
            SolverThread solverThreadA = new SolverThread(solverA);
            Thread threadA = new Thread(solverThreadA);
            threadA.start();

            Grid gridB = new Grid(cellValues);
            gridB.getCell(x, y).setValue(valueB);
            Solver solverB = new Solver(gridB);
            SolverThread solverThreadB = new SolverThread(solverB);
            Thread threadB = new Thread(solverThreadB);
            threadB.start();
    }

    private Cell getCellWithLowestPossibilities() {

        int possibilities = 2;

        while (possibilities < 10) {
            for (Cell cell : solver.getGrid().getCells()) {
                if (cell.getPossibilities().size() == possibilities) {
                    return cell;
                }
            }
            possibilities++;
        }

        throw new InvalidSudokuException();
    }
}
