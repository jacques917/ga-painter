package com.github.jacques917.ga.painter;

import com.github.jacques917.ga.painter.configuration.ApplicationModule;
import com.github.jacques917.ga.painter.configuration.GuiceFxLoader;
import com.github.jacques917.ga.painter.events.ApplicationClosingEvent;
import com.google.common.eventbus.EventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GaPainterApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(GaPainterApp.class);

    private EventBus eventBus;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        GuiceFxLoader guiceLoader = injector.getInstance(GuiceFxLoader.class);
        Parent parent = (Parent) guiceLoader.load("fx/GaPainterApp.fxml");
        Scene scene = new Scene(parent);
        primaryStage.setTitle("GA Painter");
        primaryStage.setScene(scene);
        primaryStage.show();
        eventBus = (EventBus) guiceLoader.call(EventBus.class);
    }

    @Override
    public void stop() throws Exception {
        log.info("Closing application");
        eventBus.post(new ApplicationClosingEvent());
        super.stop();
    }

    public static void main(String... args) {
        Application.launch(args);
    }

}
