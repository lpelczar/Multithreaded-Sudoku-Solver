package loader;

import com.opencsv.CSVReader;
import com.sun.istack.internal.Nullable;
import utils.IntegerChecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvLoader implements SudokuFileLoader {

    @Override
    @Nullable
    public int[] load(String filepath) {

        final int BOARD_SIZE = 81;
        int[] result = new int[BOARD_SIZE];

        try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get(filepath)))) {
            int counter = 0;
            for (String[] row : csvReader.readAll()) {
                for (String cell : row) {
                    if (isCorrectCell(cell) && counter < BOARD_SIZE) {
                        result[counter] = Integer.parseInt(cell);
                    } else {
                        return null;
                    }
                    counter++;
                }
            }
            if (counter == BOARD_SIZE) {
                return result;
            }
        } catch (IOException e) {}
        return null;
    }

    private boolean isCorrectCell(String cell) {
        final int MAX_CELL_VALUE = 9;
        return IntegerChecker.isInteger(cell) && Integer.parseInt(cell) >= 0 && Integer.parseInt(cell) <= MAX_CELL_VALUE;
    }
}
