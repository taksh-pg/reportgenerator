package com.natwest.pravesh.config;

import com.natwest.pravesh.service.ReportGenerationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReportGenerationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerationScheduler.class);

    @Value("${report.scheduler.time}")
    private String scheduledTime;

    private final ReportGenerationTask reportGenerationTask;

    public ReportGenerationScheduler(ReportGenerationTask reportGenerationTask) {
        this.reportGenerationTask = reportGenerationTask;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() throws Exception {
        logger.info("Scheduled Time :" + scheduledTime);
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        logger.info("Current Time :" + currentTime);
        if (currentTime.equals(scheduledTime)) {
            logger.info("Trigerring the report at scheduled time :" + scheduledTime);
            reportGenerationTask.generateReport();
        }
    }
}