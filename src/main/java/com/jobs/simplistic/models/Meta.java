package com.jobs.simplistic.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nikolay Boyko
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @JsonProperty("current_page")
    private int currentPage;
    private int from;
    private String path;
    @JsonProperty("per_page")
    private int perPage;
    private int to;
    private String terms;
    private String info;
}
