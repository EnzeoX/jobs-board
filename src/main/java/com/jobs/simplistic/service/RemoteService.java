package com.jobs.simplistic.service;

import com.jobs.simplistic.models.JobsData;
import com.jobs.simplistic.service.client.RemoteApiCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@Service
public class RemoteService {

    private final RemoteApiCall remoteApiCall;

    public RemoteService(ObjectMapper mapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.arbeitnow.com")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
        this.remoteApiCall = retrofit.create(RemoteApiCall.class);
    }

    public List<JobsData> fetchPages(int count) {
        List<CompletableFuture<JobsData>> futures = IntStream.rangeClosed(1, count)
                .mapToObj(pageNumber -> CompletableFuture.supplyAsync(() -> fetchPage(pageNumber)))
                .collect(Collectors.toList());

        // Combine the results of all futures
        return futures.stream()
                .map(CompletableFuture::join) // Waits for all futures to complete
                .collect(Collectors.toList());
    }

    // fetch one page
    public JobsData fetchPage(int pageNumber) {
        Call<JobsData> call = remoteApiCall.fetchJobPage(pageNumber);
        try {
            Response<JobsData> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException(String.format("Failed to fetch data, %s", response.message()));
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to fetch data, %s", e.getMessage()));
        }
    }
}
