package com.example.datastructureassignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class QuadtreeVisualization extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private QuadTree quadTree;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        quadTree = new QuadTree(new Rectangle(0, 0, WIDTH, HEIGHT), 4);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Point point = new Point(event.getX(), event.getY());
            quadTree.insert(point);
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            quadTree.draw(gc);
        });

        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Quadtree Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}