package com.github.jacques917.ga.painter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Circle implements Chromosome {

    private int xCoordinate;
    private int yCoordinate;
    private int radius;
    private int r;
    private int g;
    private int b;

    @Override
    public GraphicsData getGraphicsData() {
        Color color = new Color(r, g, b, 127); //TODO opacity - random or parameter?
        int upperLeftCornerX = this.xCoordinate - (radius / 2);
        int upperLeftCornerY = this.yCoordinate - (radius / 2);
        Ellipse2D circle = new Ellipse2D.Double(upperLeftCornerX, upperLeftCornerY, radius, radius);
        return new GraphicsData(circle, color);
    }

}
