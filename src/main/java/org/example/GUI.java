package org.example;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

public class GUI {
    private static GraphNode previousNode;
    private static GraphNode currentNode;
    private static GraphNode rootNode;

    public static void setup(BorderPane pane) {
        setupGUIElements(pane);

        pane.setOnMouseClicked(event -> {
            Bounds topBounds = pane.getTop().getBoundsInParent();
            if (event.getY() > topBounds.getMaxY()) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    List<GraphNode> list = pane.getChildren().stream()
                            .filter(node -> node instanceof GraphNode)
                            .map(node -> (GraphNode) node).toList();

                    boolean mouseIsOnNode = false;
                    for (GraphNode node : list) {
                        if (node.isMouseOnNode(event)) {
                            mouseIsOnNode = true;
                            setCurrentNode(node);
                        }
                    }

                    if (!mouseIsOnNode) {
                        GraphNode node = new GraphNode(pane);
                        node.setCenterX(event.getX());
                        node.setCenterY(event.getY());
                    }
                }
            }
        });

        pane.setOnMouseDragged(event -> {

        });
    }

    private static void setupGUIElements(BorderPane pane) {
        VBox dotsWithText = createDotsWithText();

        TextField textField = createTextField();
        Button deleteAllButton = createDeleteAllButton(pane);
        Button lineButton = createLineButton(pane);
        Button setRootNodeButton = createSetRootNodeButton();
        Button latexButton = createLatexButton();

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(textField, deleteAllButton, lineButton, setRootNodeButton, latexButton);

        HBox topContainer = new HBox(20);
        topContainer.getChildren().addAll(dotsWithText, buttonContainer);
        topContainer.setPadding(new Insets(15, 12, 15, 12));

        pane.setTop(topContainer);
    }

    private static void setCurrentNode(GraphNode node) {
        if (previousNode != null) previousNode.resetColor();
        if (node == null) {
            currentNode.resetColor();
            previousNode = null;
            currentNode = null;
            return;
        }
        if (currentNode != null) {
            previousNode = currentNode;
            previousNode.setColorRed();
        }
        currentNode = node;
        currentNode.setColorBlue();
    }

    private static VBox createDotsWithText() {
        Circle redDot = new Circle(10, MyColors.RED);
        Label redLabel = new Label("Previous Node");
        HBox redDotWithText = new HBox(10, redDot, redLabel);

        Circle blueDot = new Circle(10, MyColors.BLUE);
        Label blueLabel = new Label("Current Node");
        HBox blueDotWithText = new HBox(10, blueDot, blueLabel);

        VBox dotsWithText = new VBox(10, redDotWithText, blueDotWithText);
        dotsWithText.setAlignment(Pos.TOP_LEFT);
        return dotsWithText;
    }

    private static TextField createTextField() {
        TextField textField = new TextField("key");
        textField.setOnAction(event -> {
            String enteredText = textField.getText();
            currentNode.setText(enteredText);
            textField.setText("<key>");
        });
        return textField;
    }

    private static Button createDeleteAllButton(BorderPane pane) {
        Button deleteAllButton = TopButton.create("Delete all");
        deleteAllButton.setOnAction(actionEvent -> {
            pane.getChildren().removeIf(node -> node instanceof GraphNode || node instanceof Text || node instanceof Line);
            previousNode = null;
            currentNode = null;
            rootNode = null;
        });
        return deleteAllButton;
    }

    private static Button createLineButton(BorderPane pane) {
        Button lineButton = TopButton.create("Line");
        lineButton.setOnAction(actionEvent -> {
            Edge edge = new Edge(previousNode, currentNode);
            previousNode.addEdge(edge);
            currentNode.addEdge(edge);
            setCurrentNode(null);
            pane.getChildren().add(edge);
        });
        return lineButton;
    }

    private static Button createSetRootNodeButton() {
        Text firstPart = new Text("Set ");
        Text currentPart = new Text("current ");
        Text lastPart = new Text("node as root node");
        currentPart.setFill(MyColors.BLUE);
        TextFlow textFlow = new TextFlow(firstPart, currentPart, lastPart);

        Button setRootNodeButton = TopButton.create(textFlow);

        setRootNodeButton.setOnAction(actionEvent -> {
            if (rootNode != null) rootNode.resetColor();
            rootNode = currentNode;
            setCurrentNode(null);
            rootNode.setColorGreen();
        });

        return setRootNodeButton;
    }

    private static Button createLatexButton() {
        Button latexButton = TopButton.create("Generate LaTeX code");
        latexButton.setOnAction(actionEvent -> {
            System.out.println(CodeGenerator.generate(rootNode));
        });
        return latexButton;
    }
}
