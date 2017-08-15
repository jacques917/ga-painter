package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Chromosome;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Singleton
public class CrossoverProcessor {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    public List<Phenotype> performCrossoverOperation(List<Phenotype> parentPopulation) {
        return IntStream.rangeClosed(parentPopulation.size(),algorithmDataHolder.getPopulationSize())
                .mapToObj(i -> pairChromosomes(parentPopulation))
                .map(this::performCrossover)
                .collect(toList());
    }

    private Tuple2<Phenotype, Phenotype> pairChromosomes(List<Phenotype> parentPopulation) {
        List<Phenotype> phenotypes = ThreadLocalRandom.current().ints(0, parentPopulation.size())
                .distinct()
                .limit(2)
                .mapToObj(parentPopulation::get)
                .collect(toList());
        return Tuple.of(phenotypes.get(0), phenotypes.get(1)); //TODO refactor those gets
    }

    private Phenotype performCrossover(Tuple2<Phenotype, Phenotype> parents) {
        int crossoverIndex = ThreadLocalRandom.current().nextInt(0, algorithmDataHolder.getChromosomeCount());
        Stream<Chromosome> parent1Chromosomes = parents._1
                .getChromosomeList()
                .stream()
                .limit(crossoverIndex);
        Stream<Chromosome> parent2Chromosomes = parents._2
                .getChromosomeList()
                .stream()
                .skip(crossoverIndex);
        List<Chromosome> chromosomes = Stream.concat(parent1Chromosomes, parent2Chromosomes).collect(toList());
        return Phenotype.builder().chromosomeList(chromosomes).build();
    }
}
