package com.natwest.pravesh.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ReportGenerationTask {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerationTask.class);

    @Autowired
    private CsvFileReaderService csvFileReaderService;

    @Autowired
    private TransformationService transformationService;

    @Autowired
    private ReportGenerationService reportGenerationService;

    public void generateReport() {
        try {
            // Define file paths
            String inputFilePath = "test-scenario/input.csv";
            String referenceFilePath = "test-scenario/reference.csv";
            String outputFilePath = "test-scenario/output.csv";

            File inputFile = new File("test-scenario/input.csv");
            if (!inputFile.exists()) {
                logger.error("Input File not found !!");
                throw new IOException("Input file not found: " + inputFile.getAbsolutePath());
            }

            File outputFile = new File(outputFilePath);
            if (!outputFile.exists()) {
                boolean fileCreated = outputFile.createNewFile();
                if (!fileCreated) {
                    logger.error("Output File not found !!");
                    throw new IOException("Failed to create the output file: " + outputFile.getAbsolutePath());
                }
                logger.info("Output file created successfully!!");
            }

            // Read input and reference CSVs
            List<InputRecord> inputRecords = csvFileReaderService.readInputCsv(inputFilePath);
            List<ReferenceRecord> referenceRecords = csvFileReaderService.readReferenceCsv(referenceFilePath);

            // Transform data
            List<OutputRecord> outputRecords = transformationService.transform(inputRecords, referenceRecords);

            // Generate output report
            reportGenerationService.generateReport(outputFilePath, outputRecords);

            logger.info("Report generated successfully");

        } catch (IOException e) {
            logger.info("Error generating report", e);
        }
    }
}
