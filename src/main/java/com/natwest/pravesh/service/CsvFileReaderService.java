package com.natwest.pravesh.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CsvFileReaderService {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileReaderService.class);

    public List<InputRecord> readInputCsv(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            logger.info("Reading input file from "+  filePath);
            return new CsvToBeanBuilder<InputRecord>(reader)
                    .withType(InputRecord.class)
                    .build()
                    .parse();
        }
    }

    public List<ReferenceRecord> readReferenceCsv(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            logger.info("Reading Reference file from "+  filePath);
            return new CsvToBeanBuilder<ReferenceRecord>(reader)
                    .withType(ReferenceRecord.class)
                    .build()
                    .parse();
        }
    }
}