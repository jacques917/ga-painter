package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @FXML
    private Label iterationCounterLabel;

    @FXML
    protected void pauseButtonHandler(ActionEvent event) {
        System.out.println("pauseButtonHandler");
        iterationCounterLabel.setText(algorithmDataHolder.getFilename());
    }

}
