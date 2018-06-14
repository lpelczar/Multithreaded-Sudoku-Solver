package solver;


public class SolverThread implements Runnable {

    private Solver solver;

    @Override
    public void run() {

        boolean isSolved = solver.solve();

        if (isSolved) {
            // Wyswietl wynik
        } else {
            // Sklonuj grida i wstaw losowo liczbe
            // Zacznij nowy wątek
        }
    }
}
