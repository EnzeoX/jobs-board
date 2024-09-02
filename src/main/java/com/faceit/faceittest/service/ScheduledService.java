package com.faceit.faceittest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final static String FETCH_TIME = "0 0 11,17 ? * *";

    private final JobService jobService;
    private final RemoteService remoteService;

    @Scheduled(cron = FETCH_TIME)
    public void fetchDataFromApi() {
        CompletableFuture.supplyAsync(() -> {
            log.info("Fetching data from remote resource");
            return remoteService.fetchPage();
        }).thenAcceptAsync((jobsData -> {
            long start = System.currentTimeMillis();
            log.info("Fetching complete, processing collected data, time start: {}", start);
            jobService.processCollectedData(jobsData);
            long end = System.currentTimeMillis();
            log.info("Data processing complete, new data saved. End time: {}. Elapsed time to save and process: {} ms",
                    end,
                    (end - start));
        }));
    }
}
