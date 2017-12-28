package com.github.jacques917.ga.painter.model;

import com.google.inject.Singleton;
import javafx.scene.image.Image;

import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class AlgorithmDataHolder {

    private int populationSize = 10;
    private int chromosomeCount = 10;
    private double selectionThreshold = 0.5;
    private double mutationPropability = 0.01;
    private Image sourceImage;
    private int sourceWidth;
    private int sourceHeight;
    private Image currentLeader;
    private Long currentLeaderRank;
    private AtomicInteger iteration = new AtomicInteger(0);

    public AlgorithmDataHolder() {
    }

    public int getPopulationSize() {
        return this.populationSize;
    }

    public int getChromosomeCount() {
        return this.chromosomeCount;
    }

    public double getSelectionThreshold() {
        return this.selectionThreshold;
    }

    public double getMutationPropability() {
        return this.mutationPropability;
    }

    public Image getSourceImage() {
        return this.sourceImage;
    }

    public int getSourceWidth() {
        return this.sourceWidth;
    }

    public int getSourceHeight() {
        return this.sourceHeight;
    }

    public Image getCurrentLeader() {
        return this.currentLeader;
    }

    public Long getCurrentLeaderRank() {
        return this.currentLeaderRank;
    }

    public AtomicInteger getIteration() {
        return this.iteration;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setChromosomeCount(int chromosomeCount) {
        this.chromosomeCount = chromosomeCount;
    }

    public void setSelectionThreshold(double selectionThreshold) {
        this.selectionThreshold = selectionThreshold;
    }

    public void setMutationPropability(double mutationPropability) {
        this.mutationPropability = mutationPropability;
    }

    public void setSourceImage(Image sourceImage) {
        this.sourceImage = sourceImage;
    }

    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public void setCurrentLeader(Image currentLeader) {
        this.currentLeader = currentLeader;
    }

    public void setCurrentLeaderRank(Long currentLeaderRank) {
        this.currentLeaderRank = currentLeaderRank;
    }

    public void setIteration(AtomicInteger iteration) {
        this.iteration = iteration;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AlgorithmDataHolder)) return false;
        final AlgorithmDataHolder other = (AlgorithmDataHolder) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getPopulationSize() != other.getPopulationSize()) return false;
        if (this.getChromosomeCount() != other.getChromosomeCount()) return false;
        if (Double.compare(this.getSelectionThreshold(), other.getSelectionThreshold()) != 0) return false;
        if (Double.compare(this.getMutationPropability(), other.getMutationPropability()) != 0) return false;
        final Object this$sourceImage = this.getSourceImage();
        final Object other$sourceImage = other.getSourceImage();
        if (this$sourceImage == null ? other$sourceImage != null : !this$sourceImage.equals(other$sourceImage))
            return false;
        if (this.getSourceWidth() != other.getSourceWidth()) return false;
        if (this.getSourceHeight() != other.getSourceHeight()) return false;
        final Object this$currentLeader = this.getCurrentLeader();
        final Object other$currentLeader = other.getCurrentLeader();
        if (this$currentLeader == null ? other$currentLeader != null : !this$currentLeader.equals(other$currentLeader))
            return false;
        final Object this$currentLeaderRank = this.getCurrentLeaderRank();
        final Object other$currentLeaderRank = other.getCurrentLeaderRank();
        if (this$currentLeaderRank == null ? other$currentLeaderRank != null : !this$currentLeaderRank.equals(other$currentLeaderRank))
            return false;
        final Object this$iteration = this.getIteration();
        final Object other$iteration = other.getIteration();
        if (this$iteration == null ? other$iteration != null : !this$iteration.equals(other$iteration)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getPopulationSize();
        result = result * PRIME + this.getChromosomeCount();
        final long $selectionThreshold = Double.doubleToLongBits(this.getSelectionThreshold());
        result = result * PRIME + (int) ($selectionThreshold >>> 32 ^ $selectionThreshold);
        final long $mutationPropability = Double.doubleToLongBits(this.getMutationPropability());
        result = result * PRIME + (int) ($mutationPropability >>> 32 ^ $mutationPropability);
        final Object $sourceImage = this.getSourceImage();
        result = result * PRIME + ($sourceImage == null ? 43 : $sourceImage.hashCode());
        result = result * PRIME + this.getSourceWidth();
        result = result * PRIME + this.getSourceHeight();
        final Object $currentLeader = this.getCurrentLeader();
        result = result * PRIME + ($currentLeader == null ? 43 : $currentLeader.hashCode());
        final Object $currentLeaderRank = this.getCurrentLeaderRank();
        result = result * PRIME + ($currentLeaderRank == null ? 43 : $currentLeaderRank.hashCode());
        final Object $iteration = this.getIteration();
        result = result * PRIME + ($iteration == null ? 43 : $iteration.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof AlgorithmDataHolder;
    }

    public String toString() {
        return "AlgorithmDataHolder(populationSize=" + this.getPopulationSize() + ", chromosomeCount=" + this.getChromosomeCount() + ", selectionThreshold=" + this.getSelectionThreshold() + ", mutationPropability=" + this.getMutationPropability() + ", sourceImage=" + this.getSourceImage() + ", sourceWidth=" + this.getSourceWidth() + ", sourceHeight=" + this.getSourceHeight() + ", currentLeader=" + this.getCurrentLeader() + ", currentLeaderRank=" + this.getCurrentLeaderRank() + ", iteration=" + this.getIteration() + ")";
    }

}
