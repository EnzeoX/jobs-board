package com.jobs.simplistic.models;

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
public class Links {

    private String first;
    private String last;
    private String prev;
    private String next;
}
