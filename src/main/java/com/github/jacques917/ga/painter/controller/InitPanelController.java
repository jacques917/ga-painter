package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.UUID;

public class InitPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @FXML
    protected void loadImageButtonHandler(ActionEvent event) {
        algorithmDataHolder.setFilename(UUID.randomUUID().toString());
        System.out.println("loadImageButtonHandler");
    }

    @FXML
    protected void startAlgorithmButtonHandler(ActionEvent event) {
        System.out.println("startAlgorithmButtonHandler");
    }

}
