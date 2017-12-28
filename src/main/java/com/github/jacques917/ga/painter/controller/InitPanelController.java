package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.ImageLoadedEvent;
import com.github.jacques917.ga.painter.events.PauseAlgorithmEvent;
import com.github.jacques917.ga.painter.events.ResetAlgorithmEvent;
import com.github.jacques917.ga.painter.events.StartAlgorithmEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import io.vavr.control.Try;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;

public class InitPanelController {

    private static final Logger log = LoggerFactory.getLogger(InitPanelController.class);

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @Inject
    private EventBus eventBus;
    @FXML
    private Button startAlgorithmButton;
    @FXML
    private Button loadImageButton;
    @FXML
    private Label populationSizeLabel;
    @FXML
    private Label chromosomeCountLabel;
    @FXML
    private Slider populationSizeSlider;
    @FXML
    private Slider chromosomeCountSlider;

    @FXML
    public void initialize() {
        populationSizeSlider.valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    algorithmDataHolder.setPopulationSize(newValue.intValue());
                    populationSizeLabel.setText(valueOf(algorithmDataHolder.getPopulationSize()));
                });
        chromosomeCountSlider.valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    algorithmDataHolder.setChromosomeCount(newValue.intValue());
                    chromosomeCountLabel.setText(valueOf(algorithmDataHolder.getChromosomeCount()));
                });
        populationSizeLabel.setText(valueOf(algorithmDataHolder.getPopulationSize()));
        chromosomeCountLabel.setText(valueOf(algorithmDataHolder.getChromosomeCount()));
    }

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
        startAlgorithmButton.setDisable(true);
        loadImageButton.setDisable(true);
        populationSizeSlider.setDisable(true);
        chromosomeCountSlider.setDisable(true);

        eventBus.post(new StartAlgorithmEvent());
    }

    @FXML
    protected void resetAlgorithmButtonHandler() {
        eventBus.post(new PauseAlgorithmEvent());
        startAlgorithmButton.setDisable(false);
        loadImageButton.setDisable(false);
        populationSizeSlider.setDisable(false);
        chromosomeCountSlider.setDisable(false);

        //TODO reset
        eventBus.post(new ResetAlgorithmEvent());


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
                .map(ByteArrayInputStream::new)
                .map(Image::new)
                .andThen(algorithmDataHolder::setSourceImage)
                .onFailure(t -> new RuntimeException("Error while loading image", t));

        algorithmDataHolder.setSourceHeight((int)algorithmDataHolder.getSourceImage().getHeight());
        algorithmDataHolder.setSourceWidth((int)algorithmDataHolder.getSourceImage().getWidth());
    }

    private Window getCurrentWindow(ActionEvent event) {
        Node targetNode = (Node) event.getTarget();
        return targetNode.getScene().getWindow();
    }

}
