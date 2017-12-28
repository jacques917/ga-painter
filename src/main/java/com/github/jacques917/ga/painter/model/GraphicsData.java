package com.github.jacques917.ga.painter.model;

import java.awt.*;

public class GraphicsData {

    private final Shape shape;
    private final Color color;

    @java.beans.ConstructorProperties({"shape", "color"})
    public GraphicsData(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public Shape getShape() {
        return this.shape;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GraphicsData)) return false;
        final GraphicsData other = (GraphicsData) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$shape = this.getShape();
        final Object other$shape = other.getShape();
        if (this$shape == null ? other$shape != null : !this$shape.equals(other$shape)) return false;
        final Object this$color = this.getColor();
        final Object other$color = other.getColor();
        if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $shape = this.getShape();
        result = result * PRIME + ($shape == null ? 43 : $shape.hashCode());
        final Object $color = this.getColor();
        result = result * PRIME + ($color == null ? 43 : $color.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof GraphicsData;
    }

    public String toString() {
        return "GraphicsData(shape=" + this.getShape() + ", color=" + this.getColor() + ")";
    }
}
