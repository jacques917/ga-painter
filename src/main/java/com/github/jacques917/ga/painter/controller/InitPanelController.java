package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.ImageLoadedEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import io.vavr.control.Try;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
public class InitPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @Inject
    private EventBus eventBus;


    @FXML
    protected void loadImageButtonHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select source image");
        File selectedFile = fileChooser.showOpenDialog(getCurrentWindow(event));
        log.info("Selected file: {}", selectedFile);
        loadFile(selectedFile);
        eventBus.post(new ImageLoadedEvent());
        algorithmDataHolder.setFilename(UUID.randomUUID().toString());
        log.info("loadImageButtonHandler");
    }

    @FXML
    protected void startAlgorithmButtonHandler() {
        log.info("startAlgorithmButtonHandler");
    }

    private void loadFile(File file) {
        Path imagePath = file.toPath();
        byte[] imageBytes = Try.of(() -> imagePath)
                .mapTry(Files::readAllBytes)
                .getOrElseThrow(() -> new RuntimeException("Error while loading image"));
         algorithmDataHolder.setSourceImage(imageBytes);
    }

    private Window getCurrentWindow(ActionEvent event) {
         Node targetNode = (Node) event.getTarget();
         return targetNode.getScene().getWindow();
    }

}
