package com.github.jacques917.ga.painter.configuration;

import com.github.jacques917.ga.painter.algorithm.AlgorithmRunner;
import com.google.common.eventbus.EventBus;
import com.google.inject.Binder;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationModule implements Module {

    private final EventBus mainEventBus = new EventBus("Main event bus");

    @Override
    public void configure(Binder binder) {
        log.info("Guice module configuration");
        binder.bind(EventBus.class).toInstance(mainEventBus);
        binder.bind(AlgorithmRunner.class).asEagerSingleton();
    }

}
