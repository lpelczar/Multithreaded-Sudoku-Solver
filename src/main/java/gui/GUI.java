package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.CsvLoader;
import model.Grid;
import model.SolutionListener;
import solver.Solver;
import solver.SolverThread;
import utils.InvalidSudokuException;

import java.io.File;

public class GUI extends Application implements SolutionListener {

    private TextField[] cells = new TextField[81];
    private boolean isNonSolved = true;
    private Label threadsUsedLabel;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Sudoku Solver");

        GridPane gpLayout = new GridPane();
        gpLayout.setPadding(new Insets(20, 20, 20, 20));
        gpLayout.setHgap(8);
        gpLayout.setVgap(8);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sudoku CSV file");

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Import from CSV");
        add.setOnAction(t -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                isNonSolved = true;
                int[] values = new CsvLoader().load(file.toString());
                fillWithValues(cells, values);
            }
        });
        menuFile.getItems().add(add);
        menuBar.getMenus().add(menuFile);

        for (int i = 0; i < cells.length; i++) {
            TextField tf = new TextField();
            cells[i] = tf;
            gpLayout.getChildren().add(tf);
            GridPane.setConstraints(tf, i % 9, i / 9);
        }

        Button solveButton = new Button("Solve!");
        Label message = new Label("Enter sudoku manually or import from CSV file");
        message.setPadding(new Insets(20,0,0,0));
        threadsUsedLabel = new Label("Threads used: -");
        threadsUsedLabel.setPadding(new Insets(20,0,0,0));

        solveButton.setOnAction(event -> {
            try {
                if (this.isNonSolved) {
                    int[] sudokuArray = getSudokuIntArrayFrom(cells);
                    if (sudokuArray != null) {
                        Grid grid;
                        try {
                            grid = new Grid(sudokuArray);
                            Solver solver = new Solver(grid);
                            SolverThread solverThread = new SolverThread(solver);
                            solverThread.registerListener(this);
                            solverThread.setThreadsUsed(0);
                            Thread thread = new Thread(solverThread);
                            thread.start();
                        } catch (InvalidSudokuException e) {
                            message.setText("Invalid sudoku");
                        }
                    }
                } else {
                    message.setText("Sudoku is already solved");
                }
            }catch(IllegalArgumentException e){
                message.setText(e.getMessage());
            }
        });


        VBox v = new VBox(0);
        v.getChildren().addAll(menuBar, gpLayout, solveButton, message, threadsUsedLabel);
        v.setAlignment(Pos.TOP_CENTER);
        primaryStage.setScene(new Scene(v, 350, 510));
        primaryStage.show();
    }

    private void fillWithValues(TextField[] cells, int[] values) {
        for (int i = 0; i < cells.length; i++) {
            if(values[i] == 0) {
                cells[i].setText("");
            } else {
                cells[i].setText(String.valueOf(values[i]));
            }
        }

        for (TextField field : cells) {
            field.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        }
    }

    private int[] getSudokuIntArrayFrom(TextField[] cells) {
        int[] values = new int[cells.length];
        for (int i = 0; i < values.length; i++) {
            int val;
            String str = cells[i].getCharacters().toString();
            if (str.equals("") || str.equals("0")) {
                val = 0;
            } else if (str.matches("^[1-9]$")) {
                val = Integer.valueOf(str);
            } else {
                throw new IllegalStateException("Cell must be empty or contain number from 1 to 9");
            }

            values[i] = val;
        }
        return values;
    }

    @Override
    public void solutionFound(int[] solution, int threadsUsed) {

        Platform.runLater(() -> {
            fillWithValues(cells, solution);
            setThreadsCounter(threadsUsed);
        });

        this.isNonSolved = false;
    }

    private void setThreadsCounter(int threadsUsed) {
        threadsUsedLabel.setText("Threads used: " + String.valueOf(threadsUsed));
    }
}
