package com.github.jacques917.ga.painter.model;

import javafx.scene.image.Image;
import lombok.Data;

import java.util.List;

@Data
public class Phenotype {

    private List<Chromosome> chromosomeList;
    private Image imageRepresentation;
    private double rank;

}
