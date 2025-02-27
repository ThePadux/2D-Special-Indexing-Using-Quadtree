package com.example.datastructureassignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;




public class QuadTree {
    Rectangle boundary;
    int capacity;
    List<Point> points;
    boolean divided;
    QuadTree northeast, northwest, southeast, southwest;

    QuadTree(Rectangle boundary, int capacity) {
        this.boundary = boundary;
//        this.capacity = capacity;
        this.capacity = 2;
        this.points = new ArrayList<>();
        this.divided = false;
    }

    void subdivide() {
        double x = this.boundary.x;
        double y = this.boundary.y;
        double w = this.boundary.width / 2;
        double h = this.boundary.height / 2;

        northeast = new QuadTree(new Rectangle(x + w, y, w, h), capacity);
        northwest = new QuadTree(new Rectangle(x, y, w, h), capacity);
        southeast = new QuadTree(new Rectangle(x + w, y + h, w, h), capacity);
        southwest = new QuadTree(new Rectangle(x, y + h, w, h), capacity);

        this.divided = true;
    }

    boolean insert(Point point) {
        if (!this.boundary.contains(point)) {
            return false;
        }

        if (this.points.size() < this.capacity) {
            this.points.add(point);
            return true;
        } else {
            if (!this.divided) {
                this.subdivide();
            }

            if (northeast.insert(point)) return true;
            if (northwest.insert(point)) return true;
            if (southeast.insert(point)) return true;
            if (southwest.insert(point)) return true;
        }

        return false;
    }

    void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(boundary.x, boundary.y, boundary.width, boundary.height);

        for (Point p : points) {
            gc.setFill(Color.DARKGREEN);
            gc.fillOval(p.x - 2, p.y - 2, 4, 4);
        }

        if (divided) {
            northeast.draw(gc);
            northwest.draw(gc);
            southeast.draw(gc);
            southwest.draw(gc);
        }
    }
}

