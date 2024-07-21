package com.natwest.pravesh.test.controller;

import com.natwest.pravesh.controller.ReportController;
import com.natwest.pravesh.service.ReportGenerationTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportGenerationTask reportGenerationTask;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void generateReport_Success() throws Exception {
        doNothing().when(reportGenerationTask).generateReport();

        mockMvc.perform(get("/api/reports/generate"))
                .andExpect(status().isOk());
    }

    @Test
    void generateReport_Failure() throws Exception {
        doThrow(new RuntimeException("Report generation failed")).when(reportGenerationTask).generateReport();

        mockMvc.perform(get("/api/reports/generate"))
                .andExpect(status().isInternalServerError());
    }
}