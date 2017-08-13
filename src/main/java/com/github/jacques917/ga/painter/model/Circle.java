package com.github.jacques917.ga.painter.model;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Data
@Builder
public class Circle implements Chromosome {

    private int xCoordinate;
    private int yCoordinate;
    private int radius;
    private int r;
    private int g;
    private int b;

    @Override
    public GraphicsData getGraphicsData() {
        Color color = new Color(r,g,b,127); //TODO opacity - random or parameter?
        Ellipse2D circle = new Ellipse2D.Double(xCoordinate,yCoordinate,radius,radius);
        return new GraphicsData(circle, color);
    }

}
