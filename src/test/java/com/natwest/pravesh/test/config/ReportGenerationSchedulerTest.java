package com.natwest.pravesh.test.config;

import com.natwest.pravesh.config.ReportGenerationScheduler;
import com.natwest.pravesh.service.ReportGenerationTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

public class ReportGenerationSchedulerTest {

    @Mock
    private ReportGenerationTask reportGenerationTask;

    @InjectMocks
    private ReportGenerationScheduler reportGenerationScheduler;

    // Initialize Mockito annotations
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduledTaskTriggersReportGeneration() throws Exception {
        // Set the scheduled time to match the current time for the test
        String testScheduledTime = new SimpleDateFormat("HH:mm:ss").format(new Date());

        // Use reflection to set the private field `scheduledTime`
        Field scheduledTimeField = ReportGenerationScheduler.class.getDeclaredField("scheduledTime");
        scheduledTimeField.setAccessible(true);
        scheduledTimeField.set(reportGenerationScheduler, testScheduledTime);

        // Trigger the scheduled method
        reportGenerationScheduler.scheduleTaskWithCronExpression();

        // Verify if the reportGenerationTask.generateReport() was called
        verify(reportGenerationTask, times(1)).generateReport();
    }

    @Test
    void testScheduledTaskDoesNotTriggerReportGeneration() throws Exception {
        // Set a scheduled time that does not match the current time for the test
        String testScheduledTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis() + 60000));

        // Use reflection to set the private field `scheduledTime`
        Field scheduledTimeField = ReportGenerationScheduler.class.getDeclaredField("scheduledTime");
        scheduledTimeField.setAccessible(true);
        scheduledTimeField.set(reportGenerationScheduler, testScheduledTime);

        // Trigger the scheduled method
        reportGenerationScheduler.scheduleTaskWithCronExpression();

        // Verify that generateReport was not called
        verify(reportGenerationTask, never()).generateReport();
    }
}