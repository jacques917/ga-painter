package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.GuiRefreshEvent;
import com.github.jacques917.ga.painter.events.PauseAlgorithmEvent;
import com.github.jacques917.ga.painter.events.ResumeAlgorithmEvent;
import com.github.jacques917.ga.painter.events.StartAlgorithmEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

@Singleton
public class GuiRefreshScheduler {

    private final EventBus eventBus;
    private final Timeline timeline;

    @Inject
    public GuiRefreshScheduler(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
        timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> notifyGuiComponents()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void notifyGuiComponents() {
        eventBus.post(new GuiRefreshEvent());
    }

    @Subscribe
    public void algorithmStartedEvent(StartAlgorithmEvent event) {
        timeline.play();
    }

    @Subscribe
    public void handlePauseAlgorithmEvent(PauseAlgorithmEvent event) {
        timeline.pause();
        notifyGuiComponents();
    }

    @Subscribe
    public void handleResumeAlgorithmEvent(ResumeAlgorithmEvent event) {
        timeline.play();
    }

}
