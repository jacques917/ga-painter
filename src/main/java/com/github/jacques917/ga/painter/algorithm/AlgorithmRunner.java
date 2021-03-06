package com.github.jacques917.ga.painter.algorithm;

import com.github.jacques917.ga.painter.events.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Boolean.TRUE;

@Singleton
public class AlgorithmRunner {

    private static final Logger log = LoggerFactory.getLogger(AlgorithmRunner.class);

    @Inject
    private Algorithm algorithm;
    private final AtomicBoolean isPaused = new AtomicBoolean();
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final ReentrantLock lock = new ReentrantLock();
    private final EventBus eventBus;
    private final Condition condition;
    private final ExecutorService algorithmRunnerExecutor;

    @Inject
    public AlgorithmRunner(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
        condition = lock.newCondition();
        algorithmRunnerExecutor = Executors.newSingleThreadExecutor();
    }

    @Subscribe
    public void handleStartAlgorithmEvent(StartAlgorithmEvent event) {
        algorithm.initializeAlgorithm();
        isRunning.set(true);
        isPaused.set(false);
        algorithmRunnerExecutor.submit(this::runAlgorithm);
    }

    @Subscribe
    public void handlePauseAlgorithmEvent(PauseAlgorithmEvent event) {
        lock.lock();
        try {
            if (!isPaused.compareAndSet(false, true)) {
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
            if (!isPaused.compareAndSet(true, false)) {
                throw new RuntimeException("Algorithm is not in paused state");
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Subscribe
    public void handleRestartAlgorithmEvent(ResetAlgorithmEvent event) {
        lock.lock();
        try {
            isRunning.set(false);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Subscribe
    public void handleApplicationClosingEvent(ApplicationClosingEvent event) {
        log.info("Stopping algorithm runner thread pool");
        isRunning.set(false);
        isPaused.set(false);
        algorithmRunnerExecutor.shutdownNow();
    }

    private void runAlgorithm() {
        log.info("Starting algorithm");
        while (isRunning.get()) {
            checkIfPaused();
            runAlgorithmStep();
        }
        log.info("Stopping algorithm");
    }

    private void runAlgorithmStep() {
        if (isRunning.get()) {
            algorithm.step();
            //    eventBus.post(new AlgorithmStepFinishedEvent());
        }
    }

    private void checkIfPaused() {
        if (isPaused.get() == TRUE) {
            lock.lock();
            try {
                log.info("Pausing execution");
                condition.await();
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

}
