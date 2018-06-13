package solver;

import model.Cell;
import model.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Solver {

    private Grid grid;
    private boolean cycleFailed;

    public Solver(Grid grid) {
        this.grid = grid;
    }

    public boolean isValueLegal(int x, int y, int value) {
        return grid.getCol(x).stream().noneMatch(k -> k.getValue() == value) &&
               grid.getRow(y).stream().noneMatch(k -> k.getValue() == value) &&
               grid.getBox(grid.getBoxIdBy(x, y)).stream().noneMatch(k -> k.getValue() == value);
    }

    public void fillWithPossibilities() {
        cycleFailed = true;

        for (Cell cell : grid.getCells()) {
            cell.getPossibilities().clear();
            if (cell.getValue() == 0) {
                for (int value = 1; value <= 9; value++) {
                    if (isValueLegal(cell.getX(), cell.getY(), value)) {
                        cell.getPossibilities().add(value);
                    }
                }
            }
        }
    }

    public void countSolved(List<Cell> cells) {

        List<Cell> emptyCells = cells.stream().filter(x -> x.getValue() == 0).collect(Collectors.toList());
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (Cell cell : emptyCells) {
            for (Integer possibility : cell.getPossibilities()) {
                occurrences.putIfAbsent(possibility, 0);
                occurrences.put(possibility, occurrences.get(possibility) + 1);
            }
        }
        updateSolved(emptyCells, occurrences);
    }

    private void updateSolved(List<Cell> cells, Map<Integer, Integer> occurrences) {

        List<Integer> values = occurrences.entrySet().stream().filter(element -> element.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
        for (Integer value : values) {
            for (Cell cell : cells) {
                if (cell.getPossibilities().contains(value)) {
                    cell.setValue(value);
                    cycleFailed = false;
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }
}
