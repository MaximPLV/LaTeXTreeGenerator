package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {
    /*public static void main(String[] args) {
        System.out.println("Hello world!");
        int levels = 3;
        String[][] nodes = new String[][]{{"1"},
                                        {"2", "3"},
                                    {"4", "5", "6", "7"},
                        {"8", "9", null, "11", "12", "13", "14", "15"}};
        String tree = CodeGenerator.generate(nodes);

        try (FileWriter fileWriter = new FileWriter("C:\\Users\\maxim\\IdeaProjects\\LaTeXTreeGenerator\\src\\main\\resources\\file.txt")){
            fileWriter.write(tree);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tree);
    }*/

    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        scene.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Node node = new Node();
                node.setCenterX(event.getX());
                node.setCenterY(event.getY());
                root.getChildren().add(node);
            }
        });

        stage.setTitle("Draggable Nodes");
        stage.setScene(scene);
        stage.show();
        //GUI.run(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}