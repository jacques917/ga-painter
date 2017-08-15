package com.github.jacques917.ga.painter.model;

import javafx.scene.image.Image;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "chromosomeList")
public class Phenotype {

    private List<Chromosome> chromosomeList;
    private Image imageRepresentation;
    private Long rank;

}
