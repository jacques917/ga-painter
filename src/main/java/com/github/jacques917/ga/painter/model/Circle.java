package com.github.jacques917.ga.painter.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Chromosome {

    private int xCoordinate;
    private int yCoordinate;
    private int radius;
    private int r;
    private int g;
    private int b;

    @java.beans.ConstructorProperties({"xCoordinate", "yCoordinate", "radius", "r", "g", "b"})
    public Circle(int xCoordinate, int yCoordinate, int radius, int r, int g, int b) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Circle() {
    }

    public static CircleBuilder builder() {
        return new CircleBuilder();
    }

    @Override
    public GraphicsData getGraphicsData() {
        Color color = new Color(r, g, b, 127); //TODO opacity - random or parameter?
        int upperLeftCornerX = this.xCoordinate - (radius / 2);
        int upperLeftCornerY = this.yCoordinate - (radius / 2);
        Ellipse2D circle = new Ellipse2D.Double(upperLeftCornerX, upperLeftCornerY, radius, radius);
        return new GraphicsData(circle, color);
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Circle)) return false;
        final Circle other = (Circle) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getXCoordinate() != other.getXCoordinate()) return false;
        if (this.getYCoordinate() != other.getYCoordinate()) return false;
        if (this.getRadius() != other.getRadius()) return false;
        if (this.getR() != other.getR()) return false;
        if (this.getG() != other.getG()) return false;
        if (this.getB() != other.getB()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getXCoordinate();
        result = result * PRIME + this.getYCoordinate();
        result = result * PRIME + this.getRadius();
        result = result * PRIME + this.getR();
        result = result * PRIME + this.getG();
        result = result * PRIME + this.getB();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Circle;
    }

    public String toString() {
        return "Circle(xCoordinate=" + this.getXCoordinate() + ", yCoordinate=" + this.getYCoordinate() + ", radius=" + this.getRadius() + ", r=" + this.getR() + ", g=" + this.getG() + ", b=" + this.getB() + ")";
    }

    public static class CircleBuilder {
        private int xCoordinate;
        private int yCoordinate;
        private int radius;
        private int r;
        private int g;
        private int b;

        CircleBuilder() {
        }

        public Circle.CircleBuilder xCoordinate(int xCoordinate) {
            this.xCoordinate = xCoordinate;
            return this;
        }

        public Circle.CircleBuilder yCoordinate(int yCoordinate) {
            this.yCoordinate = yCoordinate;
            return this;
        }

        public Circle.CircleBuilder radius(int radius) {
            this.radius = radius;
            return this;
        }

        public Circle.CircleBuilder r(int r) {
            this.r = r;
            return this;
        }

        public Circle.CircleBuilder g(int g) {
            this.g = g;
            return this;
        }

        public Circle.CircleBuilder b(int b) {
            this.b = b;
            return this;
        }

        public Circle build() {
            return new Circle(xCoordinate, yCoordinate, radius, r, g, b);
        }

        public String toString() {
            return "Circle.CircleBuilder(xCoordinate=" + this.xCoordinate + ", yCoordinate=" + this.yCoordinate + ", radius=" + this.radius + ", r=" + this.r + ", g=" + this.g + ", b=" + this.b + ")";
        }
    }
}
