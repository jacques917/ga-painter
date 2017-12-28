package com.github.jacques917.ga.painter.configuration;

import com.github.jacques917.ga.painter.algorithm.AlgorithmRunner;
import com.github.jacques917.ga.painter.controller.GuiRefreshScheduler;
import com.google.common.eventbus.EventBus;
import com.google.inject.Binder;
import com.google.inject.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationModule implements Module {

    private static final Logger log = LoggerFactory.getLogger(ApplicationModule.class);

    private final EventBus mainEventBus = new EventBus("Main event bus");

    @Override
    public void configure(Binder binder) {
        log.info("Guice module configuration");
        binder.bind(EventBus.class).toInstance(mainEventBus);
        binder.bind(AlgorithmRunner.class).asEagerSingleton();
        binder.bind(GuiRefreshScheduler.class).asEagerSingleton();
    }

}
