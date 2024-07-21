package com.natwest.pravesh.service;

import com.natwest.pravesh.model.OutputRecord;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
@Service
public class ReportGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileReaderService.class);

    public void generateReport(String outputFilePath, List<OutputRecord> outputRecords) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {
            logger.info("generating report");
            writer.writeNext(new String[]{"outfield1", "outfield2", "outfield3", "outfield4", "outfield5"});

            for (OutputRecord record : outputRecords) {
                writer.writeNext(new String[]{
                        record.getOutfield1(),
                        record.getOutfield2(),
                        record.getOutfield3(),
                        record.getOutfield4() != null ? record.getOutfield4().toString() : "",
                        record.getOutfield5() != null ? record.getOutfield5().toString() : ""
                });
            }
        }
    }
}