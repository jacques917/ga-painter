package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class InitPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @FXML
    protected void loadImageButtonHandler() {
        algorithmDataHolder.setFilename(UUID.randomUUID().toString());
        log.info("loadImageButtonHandler");
    }

    @FXML
    protected void startAlgorithmButtonHandler() {
        log.info("startAlgorithmButtonHandler");
    }

}
