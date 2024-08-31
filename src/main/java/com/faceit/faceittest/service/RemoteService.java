package com.faceit.faceittest.service;

import com.faceit.faceittest.models.JobsData;
import com.faceit.faceittest.service.client.RemoteApiCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

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

    public JobsData fetchPage() {
        Call<JobsData> call = remoteApiCall.fetchJobsData();
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
