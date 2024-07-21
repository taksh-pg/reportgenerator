package com.natwest.pravesh.test.service;

import com.natwest.pravesh.model.InputRecord;
import com.natwest.pravesh.model.OutputRecord;
import com.natwest.pravesh.model.ReferenceRecord;
import com.natwest.pravesh.service.CsvFileReaderService;
import com.natwest.pravesh.service.ReportGenerationService;
import com.natwest.pravesh.service.ReportGenerationTask;
import com.natwest.pravesh.service.TransformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

class ReportGenerationTaskTest {

    @InjectMocks
    private ReportGenerationTask task;

    @Mock
    private CsvFileReaderService csvFileReaderService;

    @Mock
    private TransformationService transformationService;

    @Mock
    private ReportGenerationService reportGenerationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateReport() throws IOException {
        // Mock file checks
        File inputFile = mock(File.class);
        when(inputFile.exists()).thenReturn(true);
        File outputFile = mock(File.class);
        when(outputFile.exists()).thenReturn(true);
        when(outputFile.createNewFile()).thenReturn(true);

        List<InputRecord> inputRecords = List.of(new InputRecord());
        List<ReferenceRecord> referenceRecords = List.of(new ReferenceRecord());
        List<OutputRecord> outputRecords = List.of(new OutputRecord());

        when(csvFileReaderService.readInputCsv(anyString())).thenReturn(inputRecords);
        when(csvFileReaderService.readReferenceCsv(anyString())).thenReturn(referenceRecords);
        when(transformationService.transform(anyList(), anyList())).thenReturn(outputRecords);

        doNothing().when(reportGenerationService).generateReport(anyString(), anyList());

        task.generateReport();

        verify(csvFileReaderService, times(1)).readInputCsv(anyString());
        verify(csvFileReaderService, times(1)).readReferenceCsv(anyString());
        verify(transformationService, times(1)).transform(anyList(), anyList());
        verify(reportGenerationService, times(1)).generateReport(anyString(), anyList());
    }
}
