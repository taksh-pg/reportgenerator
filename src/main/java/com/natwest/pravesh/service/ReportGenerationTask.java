package com.natwest.pravesh.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ReportGenerationTask {

    @Autowired
    private CsvFileReaderService csvFileReaderService;

    @Autowired
    private TransformationService transformationService;

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    private LoggingService loggingService;

    public void generateReport() {
        try {
            // Define file paths
            String inputFilePath = "test-scenario/input.csv";
            String referenceFilePath = "test-scenario/reference.csv";
            String outputFilePath = "test-scenario/output.csv";

            // Read input and reference CSVs
            List<InputRecord> inputRecords = csvFileReaderService.readInputCsv(inputFilePath);
            List<ReferenceRecord> referenceRecords = csvFileReaderService.readReferenceCsv(referenceFilePath);

            // Transform data
            List<OutputRecord> outputRecords = transformationService.transform(inputRecords, referenceRecords);

            // Generate output report
            reportGenerationService.generateReport(outputFilePath, outputRecords);

            loggingService.logInfo("Report generated successfully");

        } catch (IOException e) {
            loggingService.logError("Error generating report", e);
        }
    }
}
