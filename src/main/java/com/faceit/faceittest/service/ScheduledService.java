package com.faceit.faceittest.service;

import com.faceit.faceittest.models.JobsData;
import com.faceit.faceittest.service.client.RemoteApiCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
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
            log.info("Fetching complete, processing collected data");
            jobService.processCollectedData(jobsData);
            log.info("Data processing complete, new data saved");
        }));
    }
}
