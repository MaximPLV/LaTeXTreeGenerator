package org.example;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node extends Circle {
    private double mouseX;
    private double mouseY;

    public Node() {
        super(20); // Set the radius of the circle
        setFill(Color.WHITE); // Set the fill color
        setStroke(Color.BLACK); // Set the stroke color
        setOnMousePressed(event -> {
            // Record the current mouse X and Y position on Node
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        setOnMouseDragged(event -> {
            // Calculate the new position of the node
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;
            setCenterX(getCenterX() + deltaX);
            setCenterY(getCenterY() + deltaY);

            // Update the position
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.isSecondaryButtonDown()) {
                // Get the parent and remove this node
                ((Pane) this.getParent()).getChildren().remove(this);
                event.consume();
            }
        });
    }

    private void setDeleteOption() {
        setOnMouseDragged(event -> {
            // Calculate the new position of the node
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;
            setCenterX(getCenterX() + deltaX);
            setCenterY(getCenterY() + deltaY);

            // Update the position
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }


}
