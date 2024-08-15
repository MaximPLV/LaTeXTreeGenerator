package org.example;

import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

public class TopButton {

    public static final double BUTTON_HEIGHT = 25;

    private TopButton() { }

    public static Button create(String text) {
        Button button = new Button(text);
        button.setPrefHeight(BUTTON_HEIGHT);
        return button;
    }

    public static Button create(TextFlow textFlow) {
        Button button = new Button();
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setGraphic(textFlow);
        return button;
    }
}
