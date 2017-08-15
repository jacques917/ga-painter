package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.GuiRefreshEvent;
import com.github.jacques917.ga.painter.events.PauseAlgorithmEvent;
import com.github.jacques917.ga.painter.events.ResumeAlgorithmEvent;
import com.github.jacques917.ga.painter.events.StartAlgorithmEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.valueOf;

@Slf4j
public class ControlPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @FXML
    private Label iterationCounterLabel;
    @FXML
    private Label currentLeaderRankLabel;
    @FXML
    private Button pauseOrResumeAlgorithmButton;
    @FXML
    private Label selectionThresholdLabel;
    @FXML
    private Slider selectionThresholdSlider;
    private final EventBus eventBus;
    private boolean paused = false;

    @Inject
    public ControlPanelController(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @FXML
    public void initialize() {
        selectionThresholdSlider.valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    algorithmDataHolder.setSelectionThreshold(newValue.intValue() / 100.0);
                    selectionThresholdLabel.setText(valueOf((int) (algorithmDataHolder.getSelectionThreshold() * 100)));
                });
        selectionThresholdSlider.valueProperty().setValue(algorithmDataHolder.getSelectionThreshold() * 100);
        selectionThresholdLabel.setText(valueOf((int) (algorithmDataHolder.getSelectionThreshold() * 100)));
    }

    @FXML
    protected void pauseButtonHandler() {
        if (paused) {
            resume();
        } else {
            pause();
        }
    }

    @Subscribe
    public void handleGuiRefreshEvent(GuiRefreshEvent event) {
        updateIterationCounter();
    }

    @Subscribe
    public void algorithmStartedEvent(StartAlgorithmEvent event) {
        Platform.runLater(() -> pauseOrResumeAlgorithmButton.setDisable(false));
        Platform.runLater(() -> selectionThresholdSlider.setDisable(true));
    }

    @Subscribe
    public void handlePauseAlgorithmEvent(PauseAlgorithmEvent event) {
        Platform.runLater(() -> selectionThresholdSlider.setDisable(false));
    }

    @Subscribe
    public void handleResumeAlgorithmEvent(ResumeAlgorithmEvent event) {
        Platform.runLater(() -> selectionThresholdSlider.setDisable(true));
    }

    private void updateIterationCounter() {
        String iterationNumber = algorithmDataHolder.getIteration().toString();
        Platform.runLater(() -> iterationCounterLabel.setText(iterationNumber));
        Long currentLeaderRank = algorithmDataHolder.getCurrentLeaderRank();
        Platform.runLater(() -> currentLeaderRankLabel.setText(String.valueOf(currentLeaderRank)));
    }

    private void pause() {
        pauseOrResumeAlgorithmButton.setText("Resume");
        eventBus.post(new PauseAlgorithmEvent());
        paused = true;
    }

    private void resume() {
        pauseOrResumeAlgorithmButton.setText("Pause");
        eventBus.post(new ResumeAlgorithmEvent());
        paused = false;
    }

}
