package com.jobs.simplistic.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledService {

    @Value("#{new Integer('${scheduled.data.old}')}")
    private final int olderThan = 30;

    private final String FETCH_TIME = "0 0 */1 * * *";

    private final String DELETE_TIME = "0 0 */1 * * *";

//    @Value("#{new Integer('${scheduled.pages.value')}")
    private final int pagesToCollect = 5;

    private final JobService jobService;
    private final RemoteService remoteService;

    private final AtomicBoolean isProcessRunning = new AtomicBoolean(false);
    private final AtomicBoolean isCleanPerforming = new AtomicBoolean(false);

    @PostConstruct
    public void showInfo() {
        log.info("{} info: {} - {}; {} - {}; {} - {};",
                this.getClass().getSimpleName(),
                "FETCH_TIME",
                FETCH_TIME,
                "DELETE_TIME",
                DELETE_TIME,
                "Pages to collect",
                pagesToCollect);
    }

    @Scheduled(cron = DELETE_TIME)
    public void performClean() {
        if (isCleanPerforming.get()) {
            log.warn("Clean is already performing");
            return;
        }
        log.info("Checking for old rows and performing clean");
        CompletableFuture.runAsync(() -> {
            log.info("Performing check and clean");
            log.info("Rows deleted: {}",
                    jobService.deleteOlderThan(olderThan));
        }).thenRun(() -> {
            isCleanPerforming.set(false);
        });
    }

    @Scheduled(cron = FETCH_TIME)
    public void fetchDataFromApi() {
        if (isProcessRunning.get()) {
            log.warn("Fetching already running");
            return;
        }
        isProcessRunning.set(true);
        CompletableFuture.supplyAsync(() -> {
            log.info("Fetching data from remote resource");
            return remoteService.fetchPages(pagesToCollect);
        }).thenAcceptAsync((jobsData -> {
            long start = System.currentTimeMillis();
            log.info("Fetching complete, processing collected data, time start: {}", start);
            jobService.processCollectedListOfData(jobsData);
            long end = System.currentTimeMillis();
            log.info("Data processing complete, new data saved. End time: {}. Elapsed time to save and process: {} ms",
                    end,
                    (end - start));
        })).thenRun(() -> isProcessRunning.set(false));
    }
}
