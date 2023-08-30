package food.backend.batch.scheduler;

import food.backend.batch.job.FetchFoodDataJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final FetchFoodDataJob fetchFoodDataJob;

    @Scheduled(cron = "0 0 2 * * *")
    public void fetchFoodDataSchedule() {
        Map<String, JobParameter> confMap = new HashMap<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        confMap.put("time", new JobParameter(dateFormat.format(currentDate)));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            JobExecution jobExecution = jobLauncher.run(fetchFoodDataJob.updateFoodDataJob(), jobParameters);

            log.info(">>>>>>Job Execution : {}", jobExecution.getStatus());
            log.info(">>>>>>Job getJobConfigurationName : {}", jobExecution.getJobConfigurationName());
            log.info(">>>>>>Job getJobId : {}", jobExecution.getJobId());
            log.info(">>>>>>Job getExitStatus : {}", jobExecution.getExitStatus());
            log.info(">>>>>>Job getJobInstance : {}", jobExecution.getJobInstance());
            log.info(">>>>>>Job getStepExecutions : {}", jobExecution.getStepExecutions());
            log.info(">>>>>>Job getLastUpdated : {}", jobExecution.getLastUpdated());
            log.info(">>>>>>Job getFailureExceptions : {}", jobExecution.getFailureExceptions());
        } catch (JobExecutionAlreadyRunningException
                |JobRestartException
                |JobInstanceAlreadyCompleteException
                |JobParametersInvalidException e) {
            e.getMessage();
        }

    }
}
