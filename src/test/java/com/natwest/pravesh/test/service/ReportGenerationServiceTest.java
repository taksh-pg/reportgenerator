package com.natwest.pravesh.test.service;

import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.service.ReportGenerationService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportGenerationServiceTest {

    @Test
    void testGenerateReport() throws IOException, CsvException {
        // Test data
        OutputRecord outputRecord1 = new OutputRecord();
        outputRecord1.setOutfield1("out1");
        outputRecord1.setOutfield2("out2");
        outputRecord1.setOutfield3("out3");
        outputRecord1.setOutfield4(null);
        outputRecord1.setOutfield5(123.0);

        OutputRecord outputRecord2 = new OutputRecord();
        outputRecord2.setOutfield1("out4");
        outputRecord2.setOutfield2("out5");
        outputRecord2.setOutfield3("out6");
        outputRecord2.setOutfield4(456.0);
        outputRecord2.setOutfield5(null);

        List<OutputRecord> outputRecords = Arrays.asList(outputRecord1, outputRecord2);

        // Use a temporary file for testing
        File tempFile = File.createTempFile("testOutput", ".csv");
        tempFile.deleteOnExit(); // Ensure the file is deleted after the test

        // Create a service instance
        ReportGenerationService reportGenerationService = new ReportGenerationService();

        // Act
        reportGenerationService.generateReport(tempFile.getAbsolutePath(), outputRecords);

        // Read and verify the content of the CSV file
        try (CSVReader reader = new CSVReader(new FileReader(tempFile))) {
            List<String[]> lines = reader.readAll();

            // Verify the header
            String[] header = lines.get(0);
            assertEquals("outfield1", header[0]);
            assertEquals("outfield2", header[1]);
            assertEquals("outfield3", header[2]);
            assertEquals("outfield4", header[3]);
            assertEquals("outfield5", header[4]);

            // Verify the first record
            String[] record1 = lines.get(1);
            assertEquals("out1", record1[0]);
            assertEquals("out2", record1[1]);
            assertEquals("out3", record1[2]);
            assertEquals("", record1[3]);
            assertEquals("123.0", record1[4]);

            // Verify the second record
            String[] record2 = lines.get(2);
            assertEquals("out4", record2[0]);
            assertEquals("out5", record2[1]);
            assertEquals("out6", record2[2]);
            assertEquals("456.0", record2[3]);
            assertEquals("", record2[4]);
        }
    }
}
