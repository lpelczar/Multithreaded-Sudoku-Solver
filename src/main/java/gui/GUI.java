package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private TextField[] cells = new TextField[81];

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Sudoku Solver");

        GridPane gpLayout = new GridPane();
        gpLayout.setPadding(new Insets(20, 20, 20, 20));
        gpLayout.setHgap(8);
        gpLayout.setVgap(8);

        for (int i = 0; i < cells.length; i++) {
            TextField tf = new TextField();
            cells[i] = tf;
            gpLayout.getChildren().add(tf);
            GridPane.setConstraints(tf, i % 9, i / 9);
        }

        Button solveButton = new Button("Solve!");

        VBox v = new VBox(0);
        v.getChildren().addAll(gpLayout, solveButton);
        v.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(v, 350, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
