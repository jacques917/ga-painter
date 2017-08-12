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
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
public class InitPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @Inject
    private EventBus eventBus;

    @FXML
    protected void loadImageButtonHandler(ActionEvent event) {
        processSelectFileDialog(event).ifPresent(selectedFile -> {
            log.info("Selected file: {}", selectedFile);
            loadFile(selectedFile);
            eventBus.post(new ImageLoadedEvent());
        });
    }

    @FXML
    protected void startAlgorithmButtonHandler() {
        log.info("startAlgorithmButtonHandler");
    }

    private Optional<File> processSelectFileDialog(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select source image");
        Window currentWindow = getCurrentWindow(event);
        return ofNullable(fileChooser.showOpenDialog(currentWindow));
    }

    private void loadFile(File file) {
        Path imagePath = file.toPath();
        Try.of(() -> imagePath)
                .mapTry(Files::readAllBytes)
                .andThen(algorithmDataHolder::setSourceImage)
                .onFailure(t -> new RuntimeException("Error while loading image", t));
    }

    private Window getCurrentWindow(ActionEvent event) {
         Node targetNode = (Node) event.getTarget();
         return targetNode.getScene().getWindow();
    }

}
