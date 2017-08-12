package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Singleton
class Algorithm {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @Inject
    private PhenotypeGenerator phenotypeGenerator;
    private List<Phenotype> population;

    void initializeAlgorithm() {
        population = IntStream.rangeClosed(1, algorithmDataHolder.getPopulationSize())
                .mapToObj(i -> phenotypeGenerator.createRandomPhenotype())
                .collect(toList());
    }

    void step() {
        int iteration = algorithmDataHolder.getIteration().incrementAndGet();
        log.info("Algorithm step {}", iteration);
    }

}