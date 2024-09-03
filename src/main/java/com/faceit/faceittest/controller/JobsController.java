package com.faceit.faceittest.controller;

import com.faceit.faceittest.models.Job;
import com.faceit.faceittest.models.ResponseWrapper;
import com.faceit.faceittest.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/all-jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Job>> getAllJobs() {
        ResponseWrapper<Job> wrapper = new ResponseWrapper<>(jobService.getAllJobs());
        return ResponseEntity.status(HttpStatus.OK).body(wrapper);
    }

    @GetMapping(path = "/jobs")
    public ResponseEntity<ResponseWrapper<Job>> getPaginatedJobs(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam(value = "order", defaultValue = "ASC") String order,
            @RequestParam(value = "sort", defaultValue = "slug") Optional<String[]> sort) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(20);
        String[] sortArray = sort.orElse(new String[]{"title"});
        log.info("Jobs values: {}, {}, {}, {}",
                currentPage,
                pageSize,
                order,
                sortArray);
        Page<Job> paginatedJobs = jobService.getAllPaginated(PageRequest
                .of(currentPage - 1, pageSize, Sort.by(Sort.Direction.valueOf(order), sortArray)));
        ResponseWrapper<Job> wrapper = new ResponseWrapper<>(paginatedJobs.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(wrapper);
    }
}
