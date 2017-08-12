package com.github.jacques917.ga.painter.controller;

import com.github.jacques917.ga.painter.events.ImageLoadedEvent;
import com.github.jacques917.ga.painter.model.AlgorithmDataHolder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

import static java.util.Optional.ofNullable;

public class ImagesPanelController {

    @FXML
    private ImageView sourceImage;
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

    private void updateImage() {
        ofNullable(algorithmDataHolder.getSourceImage())
                .map(ByteArrayInputStream::new)
                .map(Image::new)
                .ifPresent(sourceImage::setImage);
    }


}
