package com.natwest.pravesh.controller;

import com.natwest.pravesh.service.ReportGenerationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportGenerationTask reportGenerationTask;

    @GetMapping("/generate")
    public ResponseEntity<String> generateReport() {
        try {
            logger.info("Generating Report from API call");
            reportGenerationTask.generateReport();
            logger.info("Report generated successfully!!");
            return ResponseEntity.ok("Report generated successfully");
        } catch (Exception e) {
            logger.error("Error generating report: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating report: " + e.getMessage());
        }
    }
}