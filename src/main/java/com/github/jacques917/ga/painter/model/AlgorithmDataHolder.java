package com.github.jacques917.ga.painter.model;

import com.google.inject.Singleton;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Singleton
@Data
public class AlgorithmDataHolder {

    private int populationSize = 10;
    private byte[] sourceImage;
    private byte[] currentLeader;
    private AtomicInteger iteration = new AtomicInteger(0);

}
