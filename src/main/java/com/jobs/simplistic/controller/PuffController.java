package com.jobs.simplistic.controller;

import com.jobs.simplistic.service.ScheduledService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@RestController
@RequestMapping("data")
@RequiredArgsConstructor
public class PuffController {

    private final ScheduledService scheduledService;

    @GetMapping("get")
    public ResponseEntity<String> requestData() {
        log.info("Calling to fetch data");
        scheduledService.fetchDataFromApi();
        log.info("Process started");
        return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"Process started\"}");
    }


    @GetMapping("clean")
    public ResponseEntity<String> cleanupData() {
        log.info("Calling to cleanup data");
        scheduledService.performClean();
        log.info("Process started");
        return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"Process started\"}");
    }
}
