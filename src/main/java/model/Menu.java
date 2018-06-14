package model;

import loader.CsvLoader;
import solver.Solver;
import solver.SolverThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private final String [] MENU_OPTIONS = {"1. Easy sudoku", "2. Hard sudoku", "3. World's hardest sudoku", "0. Exit"};
    private final List <String> CORRECT_INPUT = new ArrayList<>(Arrays.asList("0", "1", "2", "3"));
    private final String EASY_UNSOLVED_FILEPATH = "src/main/resources/easy/e1_sudoku.csv";
    private final String HARD_UNSOLVED_FILEPATH = "src/main/resources/expert/ex2_sudoku.csv";
    private final String HARDEST_UNSOLVED_FILEPATH = "src/main/resources/expert/ex4_sudoku.csv";


    public void run(){

        while (true) {

            printOptions();
            String userChoice = getInput();

            switch (userChoice) {
                case "0":
                    System.exit(1);
                    break;
                case "1":
                    System.out.println("Solving easy sudoku");
                    printUnsolvedSudoku(EASY_UNSOLVED_FILEPATH);
                    solveSudoku(EASY_UNSOLVED_FILEPATH);
                    break;
                case "2":
                    System.out.println("Solving hard sudoku");
                    printUnsolvedSudoku(HARD_UNSOLVED_FILEPATH);
                    solveSudoku(HARD_UNSOLVED_FILEPATH);
                    break;
                case "3":
                    System.out.println("Solving world's hardest sudoku");
                    printUnsolvedSudoku(HARDEST_UNSOLVED_FILEPATH);
                    solveSudoku(HARDEST_UNSOLVED_FILEPATH);
                    break;

            }
        }
    }

    private void printOptions(){

        for(String option : MENU_OPTIONS){
            System.out.println(option);
        }
    }

    private String getInput(){

        String userInput ="";

        while (!CORRECT_INPUT.contains(userInput)) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                userInput = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return userInput;

    }

    private void printUnsolvedSudoku(String filepath){

        Grid grid = new Grid(new CsvLoader().load(filepath));
        System.out.println(grid.toString());
        System.out.println("");

    }

    private void solveSudoku(String filepath){

        Grid grid = new Grid(new CsvLoader().load(filepath));
        Solver solver = new Solver(grid);
        Thread thread = new Thread(new SolverThread(solver));
        thread.start();
    }

}
