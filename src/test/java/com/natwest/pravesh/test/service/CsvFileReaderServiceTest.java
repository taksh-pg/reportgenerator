package com.natwest.pravesh.test.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import com.natwest.pravesh.service.CsvFileReaderService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileReaderServiceTest {

    @Test
    void testReadInputCsv() throws IOException {
        // Setup: Create a temporary file and write test data to it
        File tempFile = File.createTempFile("inputTest", ".csv");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.append("field1,field2,field3\n");
            writer.append("value1,value2,value3\n");
            writer.append("value4,value5,value6\n");
        }

        // Create service instance
        CsvFileReaderService service = new CsvFileReaderService();

        // Act
        List<InputRecord> records = service.readInputCsv(tempFile.getAbsolutePath());

        // Verify
        assertEquals(2, records.size(), "Number of records should be 2");
        assertEquals("value1", records.get(0).getField1(), "Field1 of first record should be 'value1'");
        assertEquals("value2", records.get(0).getField2(), "Field2 of first record should be 'value2'");
        assertEquals("value3", records.get(0).getField3(), "Field3 of first record should be 'value3'");
    }

    @Test
    void testReadReferenceCsv() throws IOException {
        // Setup: Create a temporary file and write test data to it
        File tempFile = File.createTempFile("referenceTest", ".csv");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.append("refField1,refField2\n");
            writer.append("refValue1,refValue2\n");
            writer.append("refValue3,refValue4\n");
        }

        // Create service instance
        CsvFileReaderService service = new CsvFileReaderService();

        // Act
        List<ReferenceRecord> records = service.readReferenceCsv(tempFile.getAbsolutePath());

        // Verify
        assertEquals(2, records.size(), "Number of records should be 2");
    }
}