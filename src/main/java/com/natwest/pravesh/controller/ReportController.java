package com.natwest.pravesh.controller;

import com.natwest.pravesh.service.ReportGenerationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportGenerationTask reportGenerationTask;

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport() {
        try {
            reportGenerationTask.generateReport();
            return ResponseEntity.ok("Report generated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating report: " + e.getMessage());
        }
    }
}