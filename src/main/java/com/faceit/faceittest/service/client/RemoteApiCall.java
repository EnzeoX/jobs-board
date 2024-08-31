package com.faceit.faceittest.service.client;

import com.faceit.faceittest.models.JobsData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Nikolay Boyko
 */
public interface RemoteApiCall {

    @GET("api/job-board-api")
    Call<JobsData> fetchJobsData();

}
