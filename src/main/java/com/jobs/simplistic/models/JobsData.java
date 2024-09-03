package com.jobs.simplistic.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobsData {

    @JsonProperty("data")
    private List<Job> data;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("meta")
    private Meta meta;
}
