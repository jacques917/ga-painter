package com.github.jacques917.ga.painter;

import com.github.jacques917.ga.painter.configuration.ApplicationModule;
import com.github.jacques917.ga.painter.configuration.GuiceFxLoader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GaPainterApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        GuiceFxLoader guiceLoader = injector.getInstance(GuiceFxLoader.class);
        Parent parent = (Parent) guiceLoader.load("fx/GaPainterApp.fxml");
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String... args) {
        Application.launch(args);
    }

}
