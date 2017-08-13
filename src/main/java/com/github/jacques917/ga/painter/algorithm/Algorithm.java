package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Singleton
class Algorithm {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @Inject
    private PhenotypeGenerator phenotypeGenerator;
    @Inject
    private PhenotypePainter phenotypePainter;
    @Inject
    private RankCalculator rankCalculator;

    private List<Phenotype> population;

    void initializeAlgorithm() {
        population = IntStream.rangeClosed(1, algorithmDataHolder.getPopulationSize())
                .mapToObj(i -> phenotypeGenerator.createRandomPhenotype())
                .collect(toList());
    }

    void step() {
        int iteration = algorithmDataHolder.getIteration().incrementAndGet();
        Collections.shuffle(population);

        prepareImageRepresentation();
        calculateRank();

        electNewLeader();
        log.info("Algorithm step {}", iteration);
        log.info("Population rank: {}", population.stream().map(Phenotype::getRank).collect(Collectors.toList()));
    }

    private void calculateRank() {
        Image targetImage = algorithmDataHolder.getSourceImage();
        population.forEach(phenotype -> calculateRankForPhenotype(targetImage, phenotype));
    }

    private void calculateRankForPhenotype(Image targetImage, Phenotype phenotype) {
        double rank = rankCalculator.calculateRank(targetImage, phenotype.getImageRepresentation());
        phenotype.setRank(rank);
    }

    private void prepareImageRepresentation() {
        population.stream()
                .filter(phenotype -> Objects.isNull(phenotype.getImageRepresentation()))
                .forEach(phenotype -> phenotype.setImageRepresentation(phenotypePainter.paintPhenotype(phenotype)));
    }

    private void electNewLeader() {
        Phenotype phenotype = population.stream().findFirst().orElseThrow(() -> new RuntimeException("Empty population"));
        algorithmDataHolder.setCurrentLeader(phenotype.getImageRepresentation());
    }

}
