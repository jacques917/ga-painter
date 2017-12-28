package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.GuiRefreshEvent;
import com.github.jacques917.ga.painter.events.ImageLoadedEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

public class ImagesPanelController {

    private static final Logger log = LoggerFactory.getLogger(ImagesPanelController.class);

    @FXML
    private ImageView sourceImage;
    @FXML
    private ImageView currentLeader;
    @Inject
    private AlgorithmDataHolder algorithmDataHolder;

    @Inject
    public ImagesPanelController(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleImageLoadedEvent(ImageLoadedEvent event) {
        Platform.runLater(this::updateImage);
    }

    @FXML
    public void initialize() {
        URL defaultImageUrl = of(getClass())
                .map(Class::getClassLoader)
                .map(classLoader -> classLoader.getResource("img/testo.jpg"))
                .orElseThrow(() -> new RuntimeException("Default image not found"));
        Image defaultImage = new Image(defaultImageUrl.toString());
        algorithmDataHolder.setSourceImage(defaultImage);
        sourceImage.setImage(defaultImage);
        currentLeader.setImage(defaultImage);

        algorithmDataHolder.setSourceHeight((int)algorithmDataHolder.getSourceImage().getHeight());
        algorithmDataHolder.setSourceWidth((int)algorithmDataHolder.getSourceImage().getWidth());

    }

    private void updateImage() {
        ofNullable(algorithmDataHolder.getSourceImage())
                .ifPresent(sourceImage::setImage);
    }

    private void updateCurrentLeaderImage() {
        ofNullable(algorithmDataHolder.getCurrentLeader())
                .ifPresent(currentLeader::setImage);
    }

    @Subscribe
    public void handleGuiRefreshEvent(GuiRefreshEvent event) {
        updateCurrentLeaderImage();
    }

}
