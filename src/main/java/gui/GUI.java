package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private TextField[] cells = new TextField[81];

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Sudoku Solver");

        VBox v = new VBox(0);
        primaryStage.setScene(new Scene(v, 350, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
