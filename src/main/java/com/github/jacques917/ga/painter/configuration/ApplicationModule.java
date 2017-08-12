package com.github.jacques917.ga.painter.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationModule implements Module {

    @Override
    public void configure(Binder binder) {
        log.info("guice module configuration");
    }

}
