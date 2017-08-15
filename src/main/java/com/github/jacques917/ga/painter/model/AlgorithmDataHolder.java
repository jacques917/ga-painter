package com.github.jacques917.ga.painter.model;

import com.google.inject.Singleton;
import javafx.scene.image.Image;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Singleton
@Data
public class AlgorithmDataHolder {

    private int populationSize = 10;
    private int chromosomeCount = 10;
    private double selectionThreshold = 0.5;
    private double mutationPropability = 0.01;
    private Image sourceImage;
    private int sourceWidth;
    private int sourceHeight;
    private Image currentLeader;
    private Long currentLeaderRank;
    private AtomicInteger iteration = new AtomicInteger(0);

}
