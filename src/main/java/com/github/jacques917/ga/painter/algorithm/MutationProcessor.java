package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Chromosome;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class MutationProcessor {

    private static final Logger log = LoggerFactory.getLogger(MutationProcessor.class);

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @Inject
    private PhenotypeGenerator phenotypeGenerator;

    public List<Phenotype> performMutation(List<Phenotype> population) {
        double mutationPropability = algorithmDataHolder.getMutationPropability();
        Long currnet = algorithmDataHolder.getCurrentLeaderRank();
        population
                .stream()
                .parallel()
                .filter(phenotype -> ThreadLocalRandom.current().nextDouble() <= mutationPropability)
                .forEach(phenotype1 -> mutatePhenotype(phenotype1, currnet));
        return population;
    }

    private void mutatePhenotype(Phenotype phenotype, Long current) {
        if (Optional.ofNullable(current).orElse( -1L).equals(phenotype.getRank())) {
            return;
        }

        log.info("Mutating");
        int mutationIndex = ThreadLocalRandom.current().nextInt(phenotype.getChromosomeList().size());
        Chromosome chromosomeToMutate = phenotype.getChromosomeList().get(mutationIndex);
        mutateChromosome(chromosomeToMutate);
        phenotype.setImageRepresentation(null);
        phenotype.setRank(null);
    }

    private void mutateChromosome(Chromosome chromosome) {
        phenotypeGenerator.generateRandomChromosome(chromosome);
    }

}
