package utils;

import java.util.*;
import java.util.stream.Collectors;

public class PreliminarySudokuChecker {

    public static boolean isSudoku(int [] cellValues){

        List<Integer> listCellValue = Arrays.stream(cellValues).boxed().collect(Collectors.toList());
        if(!checkBoxes(listCellValue)){
            return false;
        }
        if(!checkRowsHorizontally(listCellValue)){
            return false;
        }

        return (checkRowsVertically(listCellValue));
    }

    private static boolean checkRowsHorizontally(List<Integer> cellValues){

        for(int i = 0; i < cellValues.size(); i = i + 9){
            List <Integer> row = cellValues.subList(i, i+9);
            if(!checkDuplicates(row)){
                return false;
            }

        }
        return true;

    }

    private static boolean checkRowsVertically(List <Integer> cellValues){

        for(int i = 0; i < 9; i ++ ){
            List<Integer> row = new ArrayList<>();
           for(int j = i; j < cellValues.size(); j+=9){
                   row.add(cellValues.get(j));
               }
           if(!checkDuplicates(row)){
               return false;
           }
        }
        return true;
    }

    private static boolean checkBoxes(List <Integer> cellValues){


        for(int i = 0; i < cellValues.size(); i += 27) {
            List<Integer> box = new ArrayList<>();
            for(int j = i; j < i + 27; j+=9) {
                box.add(cellValues.get(j));
                box.add(cellValues.get(j + 1));
                box.add(cellValues.get(j + 2));
            }
            if(!checkDuplicates(box)){
                return false;
            }


        }

        for(int i = 3; i < cellValues.size(); i += 27) {
            List<Integer> box = new ArrayList<>();
            for(int j = i; j < i + 27; j+=9) {
                box.add(cellValues.get(j));
                box.add(cellValues.get(j + 1));
                box.add(cellValues.get(j + 2));
            }
            if(!checkDuplicates(box)){
                return false;
            }


        }

        for(int i = 6; i < cellValues.size(); i += 27) {
            List<Integer> box = new ArrayList<>();
            for(int j = i; j < i + 27; j+=9) {
                box.add(cellValues.get(j));
                box.add(cellValues.get(j + 1));
                box.add(cellValues.get(j + 2));
            }
            if(!checkDuplicates(box)){
                return false;
            }

        }

        return true;
    }

    private static boolean checkDuplicates(List <Integer> collection){


        for (int j = 1; j < 9; j++) {
            if (Collections.frequency(collection, j) > 1) {
                return false;
            }
        }
        return true;

    }
}
