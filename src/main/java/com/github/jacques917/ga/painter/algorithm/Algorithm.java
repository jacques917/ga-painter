package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vavr.Tuple;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
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
    @Inject
    private SelectionProcessor selectionProcessor;
    @Inject
    private MutationProcessor mutationProcessor;
    @Inject
    private CrossoverProcessor crossoverProcessor;

    private List<Phenotype> population;

    void initializeAlgorithm() {
        population = IntStream.rangeClosed(1, algorithmDataHolder.getPopulationSize())
                .mapToObj(i -> phenotypeGenerator.createRandomPhenotype())
                .collect(toList());
        prepareImageRepresentation();
        calculateRank();
    }

    void step() {
        int iteration = algorithmDataHolder.getIteration().incrementAndGet();
        log.info("Iteration {} - start", iteration);
        List<Phenotype> survivors = selectionProcessor.performSelection(population);
        List<Phenotype> children = crossoverProcessor.performCrossoverOperation(survivors);
        population = Stream.concat(survivors.stream(), children.stream()).collect(toList());
        mutationProcessor.performMutation(population);
        prepareImageRepresentation();
        calculateRank();
        electNewLeader();
        log.info("Iteration {} - end", iteration);
    }

    private void calculateRank() {
        Color[][] colorArray = getTargetImageAsColorArray();
        population.stream()
                .parallel()
                .forEach(phenotype -> calculateRankForPhenotype(colorArray, phenotype));
    }

    private Color[][] getTargetImageAsColorArray() {
        Image targetImage = algorithmDataHolder.getSourceImage();
        int width = (int) targetImage.getWidth();
        int height = (int) targetImage.getHeight();
        Color[][] colorArray = new Color[width][height];
        PixelReader reader = targetImage.getPixelReader();
        IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height).mapToObj(y -> Tuple.of(x, y)))
                .forEach(integerIntegerTuple2 -> colorArray[integerIntegerTuple2._1][integerIntegerTuple2._2] =
                        reader.getColor(integerIntegerTuple2._1,integerIntegerTuple2._2));
        return colorArray;
    }

    private void calculateRankForPhenotype( Color[][] colorArray/*Image targetImage*/, Phenotype phenotype) {
        long rank = rankCalculator.calculateRank(/*targetImage*/colorArray, phenotype.getImageRepresentation());
        phenotype.setRank(rank);
    }

    private void prepareImageRepresentation() {
        population.stream()
                .parallel()
                .filter(phenotype -> Objects.isNull(phenotype.getImageRepresentation()))
                .forEach(phenotype -> phenotype.setImageRepresentation(phenotypePainter.paintPhenotype(phenotype)));
    }

    private void electNewLeader() {
        Phenotype phenotype = population
                .stream()
                .sorted(comparing(Phenotype::getRank))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empty population"));
        log.info("Leader rank: {}", phenotype.getRank());
        algorithmDataHolder.setCurrentLeader(phenotype.getImageRepresentation());
        algorithmDataHolder.setCurrentLeaderRank(phenotype.getRank());
    }

}
