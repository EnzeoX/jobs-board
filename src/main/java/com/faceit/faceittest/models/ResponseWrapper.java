package com.faceit.faceittest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper<T> {

    private List<T> data;
}
