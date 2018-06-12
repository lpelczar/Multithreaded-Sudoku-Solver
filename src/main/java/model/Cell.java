package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private int value;
    private int x;
    private int y;
    private List<Integer> possibleDigits;

    public Cell(int value, int x, int y) {
        if (value < 0 || value > 9) throw new IllegalArgumentException("Cell value must be 0-9");
        this.value = value;
        this.x = x;
        this.y = y;
        this.possibleDigits = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Integer> getPossibleDigits() {
        return possibleDigits;
    }
}
