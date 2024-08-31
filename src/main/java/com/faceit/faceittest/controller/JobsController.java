package com.faceit.faceittest.controller;

import com.faceit.faceittest.entity.JobEntity;
import com.faceit.faceittest.models.ResponseWrapper;
import com.faceit.faceittest.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class JobsController {

    private final JobService jobService;

    @GetMapping("")
    public ResponseEntity<String> simpleResponse() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"OK\"}");
    }

    @GetMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<JobEntity>> getAllJobs() {
        ResponseWrapper<JobEntity> wrapper = new ResponseWrapper<>(jobService.getAllJobs());
        return ResponseEntity.status(HttpStatus.OK).body(wrapper);
    }
}
