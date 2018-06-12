package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private int digit;
    private int x;
    private int y;
    private List<Integer> possibleDigits;

    public Cell(int digit, int x, int y) {
        if (digit < 0 || digit > 9) throw new IllegalArgumentException("Cell digit must be 0-9");
        this.digit = digit;
        this.x = x;
        this.y = y;
        this.possibleDigits = new ArrayList<>();
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
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
