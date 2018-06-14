package solver;


import model.Cell;
import model.Grid;
import utils.InvalidSudokuException;


public class SolverThread implements Runnable {

    static int threadsUsed = 0;
    private Solver solver;

    public SolverThread(Solver solver) {
        this.solver = solver;
    }

    @Override
    public void run() {

        threadsUsed++;

        boolean isSolved = solver.solve();

        if (isSolved) {
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

            Grid gridA = new Grid(cellValues);
            Grid gridB = new Grid(cellValues);
            gridA.getCell(x, y).setValue(valueA);
            gridB.getCell(x, y).setValue(valueB);
            Solver solverA = new Solver(gridA);
            Solver solverB = new Solver(gridB);
            Thread threadA = new Thread(new SolverThread(solverA));
            Thread threadB = new Thread(new SolverThread(solverB));
            threadA.start();
            threadB.start();
    }

    private Cell getCellWithLowestPossibilities() {

        int possibilities = 2;
        boolean cellFound = false;

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
