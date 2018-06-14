package solver;


import model.Cell;

public class SolverThread implements Runnable {

    static int threadsUsed = 0;
    private Solver solver;

    @Override
    public void run() {

        threadsUsed++;

        boolean isSolved = solver.solve();

        if (isSolved) {
            // Wyswietl wynik
        } else {
            splitSolving();
            // Sklonuj grida i wstaw losowo liczbe
            // Zacznij nowy wątek
        }
    }

    private void splitSolving() {
        Cell cell = getCellWithLowestPossibilities();
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

        throw new IllegalStateException("Cell with lowest possibilities not found");
    }
}
