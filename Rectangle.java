package com.example.datastructureassignment;

public class Rectangle {
    double x, y, width, height;

    Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean contains(Point point) {
        return (point.x >= this.x && point.x <= this.x + this.width &&
                point.y >= this.y && point.y <= this.y + this.height);
    }

    boolean intersects(Rectangle range) {
        return !(range.x > this.x + this.width ||
                range.x + range.width < this.x ||
                range.y > this.y + this.height ||
                range.y + range.height < this.y);
    }
}

