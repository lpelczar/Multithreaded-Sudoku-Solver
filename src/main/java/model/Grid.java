package model;

import java.util.ArrayList;
import java.util.List;

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

    public Cell getCell(int x, int y) {
        return cells[y * WIDTH + x];
    }

    public List<Cell> getRow(int y) {
        List<Cell> row = new ArrayList<>();
        for (int x = 0; x < WIDTH; x++)
            row.add(getCell(x, y));
        return row;
    }

    public List<Cell> getCol(int x) {
        List<Cell> col = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y ++)
            col.add(getCell(x, y));
        return col;
    }

    public List<Cell> getBox(int id) {
        int xOffset = 3 * (id % 3 );
        int yOffset = 3 * (id / 3);
        List<Cell> box = new ArrayList<>();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                box.add(getCell(x + xOffset, y + yOffset));
            }
        }
        return box;
    }

    @Override
    public String toString() {
        String horizontalSeparator = "+---+---+---+\n";
        String verticalSeparator = "|";
        String newline = "\n";

        StringBuilder sb = new StringBuilder();

        sb.append(horizontalSeparator);
        for (int i = 1; i <= 81; i++) {
            if (i % 9 == 1) sb.append(verticalSeparator);

            sb.append(cells[i - 1].getValue());

            if (i % 3 == 0) sb.append(verticalSeparator);
            if (i % 9 == 0) sb.append(newline);
            if (i % 27 == 0) sb.append(horizontalSeparator);
        }
        return sb.toString();
    }
}