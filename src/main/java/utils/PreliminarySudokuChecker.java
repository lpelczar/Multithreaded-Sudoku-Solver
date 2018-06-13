package utils;

import java.util.*;
import java.util.stream.Collectors;

public class PreliminarySudokuChecker {

    //private static final List <Integer> NONRECURING_VALUES = new ArrayList<>(Arrays.asList(1, 2 ,3, 4, 5, 6, 7, 8, 9));

    public static boolean isSudoku(int [] cellValues){
        if(!checkRowsHorizontally(cellValues)){
            return false;
        }
        return true;
    }

    private static boolean checkRowsHorizontally(int [] cellValues){

        List<Integer> listCellValue = Arrays.stream(cellValues).boxed().collect(Collectors.toList());

        for(int i = 0; i < listCellValue.size(); i = i + 9){
            List <Integer> row = listCellValue.subList(i, i+9);
            for(int j = 1; j <= 9; j++ ) {
                if (Collections.frequency(row, j) > 1) {
                    return false;
                }
            }

        }
        return true;

    }
}
