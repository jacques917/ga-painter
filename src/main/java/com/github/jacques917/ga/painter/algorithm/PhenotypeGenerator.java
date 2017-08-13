package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Chromosome;
import com.github.jacques917.ga.painter.model.Circle;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Singleton
public class PhenotypeGenerator {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    public Phenotype createRandomPhenotype() {
        List<Chromosome> chromosomes = IntStream.range(0, algorithmDataHolder.getPopulationSize())
                .mapToObj(value -> generateRandomChromosome())
                .collect(toList());
        Phenotype phenotype = new Phenotype();
        phenotype.setChromosomeList(chromosomes);
        return phenotype;
    }

    private Chromosome generateRandomChromosome() {
        double xSize = algorithmDataHolder.getSourceImage().getWidth();
        double ySize = algorithmDataHolder.getSourceImage().getHeight();
        double maxRadius = (xSize + ySize) / 2; //TODO what should be there?
        int x = ThreadLocalRandom.current().nextInt(0, (int) xSize);
        int y = ThreadLocalRandom.current().nextInt(0, (int) ySize);
        int radius = ThreadLocalRandom.current().nextInt(1, (int) maxRadius); //TODO min radius?
        int r = ThreadLocalRandom.current().nextInt(0, 255);
        int g = ThreadLocalRandom.current().nextInt(0, 255);
        int b = ThreadLocalRandom.current().nextInt(0, 255);
        return Circle.builder()
                .xCoordinate(x)
                .yCoordinate(y)
                .radius(radius)
                .r(r)
                .g(g)
                .b(b)
                .build();
    }

}
