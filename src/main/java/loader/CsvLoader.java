package loader;

import com.opencsv.CSVReader;
import com.sun.istack.internal.Nullable;
import utils.ByteChecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvLoader implements SudokuFileLoader {

    @Override
    @Nullable
    public byte[] load(String filepath) {

        final int BOARD_SIZE = 81;
        byte[] result = new byte[BOARD_SIZE];

        try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get(filepath)))) {
            int counter = 0;
            for (String[] row : csvReader.readAll()) {
                for (String cell : row) {
                    if (isCorrectCell(cell) && counter < BOARD_SIZE) {
                        result[counter] = Byte.parseByte(cell);
                    } else {
                        return null;
                    }
                    counter++;
                }
            }
            if (counter == BOARD_SIZE) {
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isCorrectCell(String cell) {
        final int MAX_CELL_VALUE = 9;
        return ByteChecker.isByte(cell) && Byte.parseByte(cell) >= 0 && Byte.parseByte(cell) <= MAX_CELL_VALUE;
    }
}
