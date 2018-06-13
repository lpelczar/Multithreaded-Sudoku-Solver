package solver;

import model.Cell;
import model.Grid;


public class Solver {

    private Grid grid;

    public Solver(Grid grid) {
        this.grid = grid;
    }

    public boolean isValueLegal(int x, int y, int value) {
        return grid.getCol(x).stream().noneMatch(k -> k.getValue() == value) &&
               grid.getRow(y).stream().noneMatch(k -> k.getValue() == value) &&
               grid.getBox(grid.getBoxIdBy(x, y)).stream().noneMatch(k -> k.getValue() == value);
    }

    public void fillWithPossibilities() {
        for (Cell cell : grid.getCells()) {
            if (cell.getValue() == 0) {
                for (int value = 1; value <= 9; value++) {
                    if (isValueLegal(cell.getX(), cell.getY(), value)) {
                        cell.getPossibilities().add(value);
                    }
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }
}
