package model;

import loader.CsvLoader;
import loader.SudokuFileLoader;
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
                    break;
                case "2":
                    System.out.println("Solving hard sudoku");
                    break;
                case "3":
                    System.out.println("Solving world's hardest sudoku");
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

    }

    private void solveSudoku(String filepath){

        System.out.println("Solving easy sudoku");
        Grid grid = new Grid(new CsvLoader().load("src/main/resources/easy/e1_sol.csv"));
        Solver solver = new Solver(grid);
        Thread thread = new Thread(new SolverThread(solver));
        thread.start();
    }

}
