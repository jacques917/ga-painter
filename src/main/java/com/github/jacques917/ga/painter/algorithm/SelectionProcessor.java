package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Slf4j
@Singleton
public class SelectionProcessor {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    public List<Phenotype> performSelection(List<Phenotype> population) {
        return population.stream()
                 .sorted(comparing(Phenotype::getRank).reversed())
                 .skip(calculateNumberOfPhenotypesToKill())
                 .collect(Collectors.toList());
    }

    private int calculateNumberOfPhenotypesToKill() {
        int threshold = (int) (algorithmDataHolder.getPopulationSize() * algorithmDataHolder.getSelectionThreshold());
        int survivorCount = Math.max(threshold, 2); //at least 2 phenotypes must survive
        return algorithmDataHolder.getPopulationSize() - survivorCount;
    }

}
