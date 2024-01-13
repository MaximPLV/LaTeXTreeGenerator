package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class GUI {
    public static void run(Stage stage) {
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

        BorderPane root = new BorderPane();

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button saveButton = new Button("Save");


        addButton.setOnAction(event -> {
            System.out.println(222222222);
        });

        deleteButton.setOnAction(event -> {
            // Delete button logic
        });

        saveButton.setOnAction(event -> {
            // Save button logic
        });

        HBox buttonContainer = new HBox(10); // 10 is the spacing between buttons
        buttonContainer.getChildren().addAll(addButton, deleteButton, saveButton);
        buttonContainer.setPadding(new Insets(15, 12, 15, 12));
        buttonContainer.setSpacing(10);
        buttonContainer.setAlignment(Pos.CENTER);


        root.setTop(buttonContainer);

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);

        scene.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Node node = new Node();
                node.setCenterX(event.getX());
                node.setCenterY(event.getY());
                root.getChildren().add(node);
            }
        });


        stage.show();
    }
}
