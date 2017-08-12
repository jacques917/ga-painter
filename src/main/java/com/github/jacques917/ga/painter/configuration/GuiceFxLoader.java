package com.github.jacques917.ga.painter.configuration;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

import static java.util.Optional.of;

public class GuiceFxLoader implements Callback<Class<?>, Object> {

    @Inject
    private Injector injector;

    public Object load(String fxmlName) {
        try {
            URL url = getFxmlUrl(fxmlName);
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(this);
            loader.setLocation(url);
            return loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private URL getFxmlUrl(String fxmlName) {
        return of(getClass())
                .map(Class::getClassLoader)
                .map(classLoader -> classLoader.getResource(fxmlName))
                .orElseThrow(() -> new RuntimeException("Fxml file not found"));
    }

    @Override
    public Object call(Class<?> controller) {
        return injector.getInstance(controller);
    }

}
