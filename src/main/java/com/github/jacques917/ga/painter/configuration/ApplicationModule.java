package com.github.jacques917.ga.painter.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ApplicationModule implements Module {

    @Override
    public void configure(Binder binder) {
        System.out.println("guice module configuration");
    }

}
