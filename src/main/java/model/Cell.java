package model;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    private int value;
    private int x;
    private int y;
    private Set<Integer> possibilities;

    public Cell(int value, int x, int y) {
        if (value < 0 || value > 9) throw new IllegalArgumentException("Cell value must be 0-9");
        this.value = value;
        this.x = x;
        this.y = y;
        this.possibilities = new HashSet<>();
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

    public Set<Integer> getPossibilities() {
        return possibilities;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
