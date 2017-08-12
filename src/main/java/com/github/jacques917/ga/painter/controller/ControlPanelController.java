package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControlPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @FXML
    private Label iterationCounterLabel;

    @FXML
    protected void pauseButtonHandler() {
        log.info("pauseButtonHandler");
        iterationCounterLabel.setText(algorithmDataHolder.getFilename());
    }

}
