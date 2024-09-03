package com.jobs.simplistic.service.client;

import com.jobs.simplistic.models.JobsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Nikolay Boyko
 */
public interface RemoteApiCall {

    // fetch one page
    @GET("api/job-board-api")
    Call<JobsData> fetchJobsData();


    //fetch by page number
    @GET("api/job-board-api")
    Call<JobsData> fetchJobPage(@Query("page") int page);
}
