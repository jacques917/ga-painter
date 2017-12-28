package com.github.jacques917.ga.painter.algorithm;

import com.google.inject.Singleton;
import io.vavr.Tuple;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

@Singleton
class RankCalculator {

    private static final Logger log = LoggerFactory.getLogger(RankCalculator.class);


    long calculateRank(Color[][] colors, Image phenotypeRepresentation) {
        int width = colors.length;
        int height = colors[0].length;
        PixelReader phenotypeRepresentationPixelReader = phenotypeRepresentation.getPixelReader();
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height).mapToObj(y -> Tuple.of(x, y)))
                .map(tuple -> colorDifference(colors[tuple._1][tuple._2], phenotypeRepresentationPixelReader.getColor(tuple._1, tuple._2)))
                .mapToLong(Long::longValue)
                .sum();
    }

    private long colorDifference(Color color1, Color color2) {
        long diffValue = 0;
        diffValue += Math.abs(convert(color1.getRed()) - convert(color2.getRed()));
        diffValue += Math.abs(convert(color1.getGreen()) - convert(color2.getGreen()));
        diffValue += Math.abs(convert(color1.getBlue()) - convert(color2.getBlue()));
        return diffValue;
    }

    private int convert(double doubleVal) {
        return (int) (doubleVal * 255);
    }

}
