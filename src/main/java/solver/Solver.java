package solver;

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
}
