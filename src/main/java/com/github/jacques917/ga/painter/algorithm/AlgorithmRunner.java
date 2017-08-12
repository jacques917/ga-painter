package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.events.AlgorithmStepFinishedEvent;
import com.github.jacques917.ga.painter.events.PauseAlgorithmEvent;
import com.github.jacques917.ga.painter.events.ResumeAlgorithmEvent;
import com.github.jacques917.ga.painter.events.StartAlgorithmEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Boolean.FALSE;

@Slf4j
@Singleton
public class AlgorithmRunner {

    @Inject
    private Algorithm algorithm;
    private final AtomicBoolean isRunning = new AtomicBoolean();
    private final EventBus eventBus;
    private final ReentrantLock lock;
    private final Condition condition;

    @Inject
    public AlgorithmRunner(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    @Subscribe
    public void handleStartAlgorithmEvent(StartAlgorithmEvent event) {
        if (!isRunning.compareAndSet(false, true)) {
            throw new RuntimeException("Algorithm already running");
        }
        algorithm.initializeAlgorithm();
        CompletableFuture.runAsync(this::runAlgorithm);
    }

    @Subscribe
    public void handlePauseAlgorithmEvent(PauseAlgorithmEvent event) {
        lock.lock();
        try {
            if (!isRunning.compareAndSet(true, false)) {
                throw new RuntimeException("Algorithm is not running");
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Subscribe
    public void handleResumeAlgorithmEvent(ResumeAlgorithmEvent event) {
        lock.lock();
        try {
            if (!isRunning.compareAndSet(false, true)) {
                throw new RuntimeException("Algorithm is not in paused state");
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void runAlgorithm() {
        log.info("start");
        while (true) {
            checkIfPaused();
            algorithm.step();
            eventBus.post(new AlgorithmStepFinishedEvent());
        }
    }

    private void checkIfPaused() {
        if (isRunning.get() == FALSE) {
            lock.lock();
            try {
                log.info("Pausing execution");
                condition.awaitUninterruptibly();
            } finally {
                lock.unlock();
            }
        }
    }

}
