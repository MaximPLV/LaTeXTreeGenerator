package org.example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
    private GraphNode start;
    private GraphNode end;

    public Edge(GraphNode start, GraphNode end) {
        if (start == null || end == null) throw new IllegalArgumentException("Edge must have start node and end node");

        this.start = start;
        this.end = end;

        this.setStroke(MyColors.EDGE);
        this.setStrokeWidth(3);
        this.setOpacity(0.5);
        this.setMouseTransparent(true);

        this.startXProperty().bind(start.centerXProperty());
        this.startYProperty().bind(start.centerYProperty());
        this.endXProperty().bind(end.centerXProperty());
        this.endYProperty().bind(end.centerYProperty());
    }

    public GraphNode getStart() {
        return start;
    }

    public GraphNode getEnd() {
        return end;
    }
}
