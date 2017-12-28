package com.github.jacques917.ga.painter.model;

import javafx.scene.image.Image;

import java.util.List;

public class Phenotype {

    private List<Chromosome> chromosomeList;
    private Image imageRepresentation;
    private Long rank;

    @java.beans.ConstructorProperties({"chromosomeList", "imageRepresentation", "rank"})
    public Phenotype(List<Chromosome> chromosomeList, Image imageRepresentation, Long rank) {
        this.chromosomeList = chromosomeList;
        this.imageRepresentation = imageRepresentation;
        this.rank = rank;
    }

    public Phenotype() {
    }

    public static PhenotypeBuilder builder() {
        return new PhenotypeBuilder();
    }

    public List<Chromosome> getChromosomeList() {
        return this.chromosomeList;
    }

    public Image getImageRepresentation() {
        return this.imageRepresentation;
    }

    public Long getRank() {
        return this.rank;
    }

    public void setChromosomeList(List<Chromosome> chromosomeList) {
        this.chromosomeList = chromosomeList;
    }

    public void setImageRepresentation(Image imageRepresentation) {
        this.imageRepresentation = imageRepresentation;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Phenotype)) return false;
        final Phenotype other = (Phenotype) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$chromosomeList = this.getChromosomeList();
        final Object other$chromosomeList = other.getChromosomeList();
        if (this$chromosomeList == null ? other$chromosomeList != null : !this$chromosomeList.equals(other$chromosomeList))
            return false;
        final Object this$imageRepresentation = this.getImageRepresentation();
        final Object other$imageRepresentation = other.getImageRepresentation();
        if (this$imageRepresentation == null ? other$imageRepresentation != null : !this$imageRepresentation.equals(other$imageRepresentation))
            return false;
        final Object this$rank = this.getRank();
        final Object other$rank = other.getRank();
        if (this$rank == null ? other$rank != null : !this$rank.equals(other$rank)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $chromosomeList = this.getChromosomeList();
        result = result * PRIME + ($chromosomeList == null ? 43 : $chromosomeList.hashCode());
        final Object $imageRepresentation = this.getImageRepresentation();
        result = result * PRIME + ($imageRepresentation == null ? 43 : $imageRepresentation.hashCode());
        final Object $rank = this.getRank();
        result = result * PRIME + ($rank == null ? 43 : $rank.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Phenotype;
    }

    public String toString() {
        return "Phenotype(imageRepresentation=" + this.getImageRepresentation() + ", rank=" + this.getRank() + ")";
    }

    public static class PhenotypeBuilder {
        private List<Chromosome> chromosomeList;
        private Image imageRepresentation;
        private Long rank;

        PhenotypeBuilder() {
        }

        public Phenotype.PhenotypeBuilder chromosomeList(List<Chromosome> chromosomeList) {
            this.chromosomeList = chromosomeList;
            return this;
        }

        public Phenotype.PhenotypeBuilder imageRepresentation(Image imageRepresentation) {
            this.imageRepresentation = imageRepresentation;
            return this;
        }

        public Phenotype.PhenotypeBuilder rank(Long rank) {
            this.rank = rank;
            return this;
        }

        public Phenotype build() {
            return new Phenotype(chromosomeList, imageRepresentation, rank);
        }

        public String toString() {
            return "Phenotype.PhenotypeBuilder(chromosomeList=" + this.chromosomeList + ", imageRepresentation=" + this.imageRepresentation + ", rank=" + this.rank + ")";
        }
    }
}
