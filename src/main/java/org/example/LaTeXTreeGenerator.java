package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LaTeXTreeGenerator extends Application {
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("LaTeX Tree Generator");
        stage.setScene(scene);
        GUI.setup(pane);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}