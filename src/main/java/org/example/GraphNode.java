package org.example;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Comparator;
import java.util.List;

public class GraphNode extends Rectangle {
    private static final double PADDING = 3;
    private static final double HEIGHT = 20;
    private double mouseX;
    private double mouseY;
    private final Text text;
    private final List<Edge> edges;

    public GraphNode(Pane pane) {
        super();
        setFill(MyColors.NODE);
        setStroke(MyColors.BACKGROUND);

        this.text = new Text("<key>");
        text.boundsInParentProperty().addListener((observable, oldBounds, newBounds) -> {
            this.setWidth(PADDING + newBounds.getWidth() + PADDING);
        });
        this.setHeight(HEIGHT);
        text.xProperty().bind(this.xProperty().add(PADDING));
        text.yProperty().bind(this.yProperty().add(text.getBoundsInLocal().getHeight() - PADDING));
        text.setMouseTransparent(true);


        setOnMousePressed();

        setOnMouseDragged();

        setOnMouseClicked();

        pane.getChildren().addAll(this, text);

        edges = new SafeArrayList<>();
    }

    private void setOnMousePressed() {
        setOnMousePressed(event -> {
            // Record the current mouse X and Y position on Node
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }

    private void setOnMouseDragged() {
        setOnMouseDragged(event -> {
            // Calculate the new position of the node
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;
            setX(getX() + deltaX);
            setY(getY() + deltaY);

            // Update the position
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }

    private void setOnMouseClicked() {
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                ((Pane) this.getParent()).getChildren().removeAll(edges);
                ((Pane) this.getParent()).getChildren().removeAll(this, text);
            }
        });
    }

    public boolean isMouseOnNode(MouseEvent event) {
        return this.contains(event.getX(), event.getY());
    }

    public void setColorRed() {
        setFill(MyColors.RED);
    }

    public void setColorGreen() {
        setFill(MyColors.GREEN);
    }

    public void setColorBlue() {
        setFill(MyColors.BLUE);
    }

    public void resetColor() {
        setFill(MyColors.BACKGROUND);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public SafeArrayList<GraphNode> getChildren() {
        SafeArrayList<GraphNode> children = new SafeArrayList<>();
        for (int j = 0; j < edges.size(); j++) {
            children.add(j, edges.get(j).getStart() == this ? edges.get(j).getEnd() : edges.get(j).getStart());
            children.get(j).getEdges().remove(edges.get(j));
        }
        children.sort(Comparator.comparing(GraphNode::getCenterX));
        return children;
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean isLeaf() {
        return edges.isEmpty();
    }

    public double getCenterX() {
        return getX() + getWidth()/2;
    }

    public double getCenterY() {
        return getY() + getHeight()/2;
    }

    public DoubleBinding centerXProperty() {
        return xProperty().add(widthProperty().divide(2));
    }

    public DoubleBinding centerYProperty() {
        return yProperty().add(heightProperty().divide(2));
    }

    public void setCenterX(double x) {
        setX(x - getWidth()/2);
    }

    public void setCenterY(double y) {
        setY(y - getHeight()/2);
    }
}
