package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.github.jacques917.ga.painter.model.Chromosome;
import com.github.jacques917.ga.painter.model.GraphicsData;
import com.github.jacques917.ga.painter.model.Phenotype;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Singleton
class PhenotypePainter {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    Image paintPhenotype(Phenotype phenotype) {
        BufferedImage bufferedImage = prepareEmptyBufferedImage();
        Graphics2D graphics2D = bufferedImage.createGraphics();
        phenotype.getChromosomeList()
                .forEach(chromosome -> drawShape(chromosome, graphics2D));
        graphics2D.dispose();
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    private BufferedImage prepareEmptyBufferedImage() {
        Optional<Image> sourceImageOptional = ofNullable(algorithmDataHolder.getSourceImage());
        int width = sourceImageOptional
                .map(Image::getWidth)
                .map(Double::intValue)
                .orElse(1);
        int height = sourceImageOptional
                .map(Image::getHeight)
                .map(Double::intValue)
                .orElse(1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setWhiteBackground(bufferedImage);
        return bufferedImage;
    }

    private void setWhiteBackground(BufferedImage bufferedImage) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        graphics.dispose();
    }

    private void drawShape(Chromosome chromosome, Graphics2D graphics2D) {
        GraphicsData graphicsData = chromosome.getGraphicsData();
        graphics2D.setPaint(graphicsData.getColor());
        graphics2D.fill(graphicsData.getShape());
    }

}
