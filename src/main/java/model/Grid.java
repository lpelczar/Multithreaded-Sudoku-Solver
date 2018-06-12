package model;

public class Grid {

    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private Cell[] cells;

    public Grid(int[] cellValues) {
        //TODO: validate cells length (81) and check is it a valid sudoku
        initializeCellsFrom(cellValues);
    }

    private void initializeCellsFrom(int[] cellValues) {
        cells = new Cell[81];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                int value = cellValues[index];
                cells[index] = new Cell(value, x, y);
            }
        }
    }
}