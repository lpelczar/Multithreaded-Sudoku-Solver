package model;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private static final int NUMBER_OF_CELLS = 81;
    private Cell[] cells;

    public Grid(int[] cellValues) {
        if(cellValues.length != NUMBER_OF_CELLS){
            throw new IllegalArgumentException("Table must contians 81 elements");
        }
        //TODO: validate cells length (81) and check is it a valid sudoku
        initializeCellsFrom(cellValues);
    }

    private void initializeCellsFrom(int[] cellValues) {
        cells = new Cell[NUMBER_OF_CELLS];
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

    public int getBoxIdBy(int x, int y) {
        if (x >= 0 && x <= 2 && y >= 0 && y <= 2) return 0;
        if (x >= 3 && x <= 5 && y >= 0 && y <= 2) return 1;
        if (x >= 6 && x <= 8 && y >= 0 && y <= 2) return 2;
        if (x >= 0 && x <= 2 && y >= 3 && y <= 5) return 3;
        if (x >= 3 && x <= 5 && y >= 3 && y <= 5) return 4;
        if (x >= 6 && x <= 8 && y >= 3 && y <= 5) return 5;
        if (x >= 0 && x <= 2 && y >= 6 && y <= 8) return 6;
        if (x >= 3 && x <= 5 && y >= 6 && y <= 8) return 7;
        if (x >= 6 && x <= 8 && y >= 6 && y <= 8) return 8;
        return -1;
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