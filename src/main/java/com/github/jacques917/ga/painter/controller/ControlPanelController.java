package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.AlgorithmStepFinishedEvent;
import com.github.jacques917.ga.painter.events.PauseAlgorithmEvent;
import com.github.jacques917.ga.painter.events.ResumeAlgorithmEvent;
import com.github.jacques917.ga.painter.events.StartAlgorithmEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControlPanelController {

    @Inject
    private AlgorithmDataHolder algorithmDataHolder;
    @FXML
    private Label iterationCounterLabel;
    @FXML
    private Button pauseOrResumeAlgorithmButton;
    private final EventBus eventBus;
    private boolean paused = false;
    private final Timeline timeline;

    @Inject
    public ControlPanelController(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateIterationCounter();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
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
    public void algorithmStartedEvent(StartAlgorithmEvent event) {
        Platform.runLater(() -> pauseOrResumeAlgorithmButton.setDisable(false));
        timeline.play();
    }

    @Subscribe
    public void handlePauseAlgorithmEvent(PauseAlgorithmEvent event) {
        timeline.pause();
        updateIterationCounter();
    }

    @Subscribe
    public void handleResumeAlgorithmEvent(ResumeAlgorithmEvent event) {
        timeline.play();
    }

    @Subscribe
    public void handleAlgorithmStepFinishedEvent(AlgorithmStepFinishedEvent event) {
        String iterationNumber = algorithmDataHolder.getIteration().toString();
        Platform.runLater(() -> iterationCounterLabel.setText(iterationNumber));
    }

    private void updateIterationCounter() {
        String iterationNumber = algorithmDataHolder.getIteration().toString();
        Platform.runLater(() -> iterationCounterLabel.setText(iterationNumber));
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
