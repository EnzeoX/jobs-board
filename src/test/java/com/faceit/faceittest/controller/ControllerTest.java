package com.faceit.faceittest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nikolay Boyko
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = JobsController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test controller for 200 status code")
    public void test_responseStatusOK() throws Exception {
        mockMvc.perform(get("/api/v1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test controller for OK status in body")
    public void test_responseBodyOK() throws Exception {
        mockMvc.perform(get("/api/v1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"status\":\"OK\"}"));
    }
}
