package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Chromosome;
import com.github.jacques917.ga.painter.model.Circle;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Singleton
class PhenotypeGenerator {

    private static final Logger log = LoggerFactory.getLogger(PhenotypeGenerator.class);

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    Phenotype createRandomPhenotype() {
        List<Chromosome> chromosomes = IntStream.range(0, algorithmDataHolder.getChromosomeCount())
                .mapToObj(value -> new Circle())
                .map(this::generateRandomChromosome)
                .collect(toList());
        Phenotype phenotype = new Phenotype();
        phenotype.setChromosomeList(chromosomes);
        return phenotype;
    }

    Chromosome generateRandomChromosome(Chromosome chromosome) { //TODO create specialized method for each chromosome implementation
        Circle circle = (Circle) chromosome;
        double xSize = algorithmDataHolder.getSourceImage().getWidth();
        double ySize = algorithmDataHolder.getSourceImage().getHeight();
        double maxRadius = (xSize + ySize) / 2; //TODO what should be there?
        int x = ThreadLocalRandom.current().nextInt(0, (int) xSize);
        circle.setXCoordinate(x);
        int y = ThreadLocalRandom.current().nextInt(0, (int) ySize);
        circle.setYCoordinate(y);
        int radius = ThreadLocalRandom.current().nextInt(1, (int) maxRadius); //TODO min radius?
        circle.setRadius(radius);
        int r = ThreadLocalRandom.current().nextInt(0, 255);
        circle.setR(r);
        int g = ThreadLocalRandom.current().nextInt(0, 255);
        circle.setG(g);
        int b = ThreadLocalRandom.current().nextInt(0, 255);
        circle.setB(b);
        return circle;
    }

}
