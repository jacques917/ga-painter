package com.github.jacques917.ga.painter.model;

import com.google.inject.Singleton;
import lombok.Data;

@Singleton
@Data
public class AlgorithmDataHolder {

    private Integer counter = 0;
    private String filename;

}
