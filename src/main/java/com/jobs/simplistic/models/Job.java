package com.jobs.simplistic.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("remote")
    private boolean remote;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tags")
    private List<String> jobTags;

    @JsonProperty("job_types")
    private List<String> jobTypes;

    @JsonProperty("location")
    private String location;

    @JsonProperty("created_at")
    private Date createdAt;
}
