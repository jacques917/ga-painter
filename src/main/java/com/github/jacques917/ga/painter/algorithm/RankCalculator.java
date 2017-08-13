package com.github.jacques917.ga.painter.algorithm;

import com.google.inject.Singleton;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
@Singleton
class RankCalculator {

    double calculateRank(Image targetImage, Image phenotypeRepresentation) {
        checkIfDimensionsMatch(targetImage, phenotypeRepresentation);
        int width = (int) targetImage.getWidth();
        int height = (int) targetImage.getHeight();
        PixelReader targetImagePixelReader = targetImage.getPixelReader();
        PixelReader phenotypeRepresentationPixelReader = phenotypeRepresentation.getPixelReader();
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height).mapToObj(y -> Tuple.of(x, y)))
                .map(tuple -> pixelDifference(tuple, targetImagePixelReader, phenotypeRepresentationPixelReader))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double pixelDifference(Tuple2<Integer, Integer> coordinates, PixelReader pixelReader1, PixelReader pixelReader2) {
        Color pixel1Color = pixelReader1.getColor(coordinates._1, coordinates._2);
        Color pixel2Color = pixelReader2.getColor(coordinates._1, coordinates._2);
        return colorDifference(pixel1Color, pixel2Color);
    }

    private double colorDifference(Color color1, Color color2) {
        double diffValue = 0;
        diffValue += Math.abs(color1.getRed() - color2.getRed());
        diffValue += Math.abs(color1.getGreen() - color2.getGreen());
        diffValue += Math.abs(color1.getBlue() - color2.getBlue());
        return diffValue;
    }

    private void checkIfDimensionsMatch(Image targetImage, Image phenotypeRepresentation) {
        if (targetImage.getHeight() != phenotypeRepresentation.getHeight() ||
                targetImage.getWidth() != phenotypeRepresentation.getWidth()) {
            throw new RuntimeException("Dimenstions mismatch");
        }
    }

}
