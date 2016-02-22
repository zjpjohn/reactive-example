package com.sf.job.service;

import com.sf.job.JobDefinition;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobExecution;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobExecutionRepository;
import com.sf.job.repository.JobRepository;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class DefaultJobService implements JobService {

    private final JobRepository jobRepository;
    private final JobExecutionRepository jobExecutionRepository;

    public DefaultJobService(JobRepository jobRepository, JobExecutionRepository jobExecutionRepository) {
        this.jobRepository = jobRepository;
        this.jobExecutionRepository = jobExecutionRepository;
    }

    @Override
    public Job addJob(JobDefinition jobDefinition) {
        return jobRepository.create(jobDefinition.getJobName(),jobDefinition.getIdKey());
    }

    @Override
    public Job getJob(JobName jobName) {
        return jobRepository.read(jobName);
    }

    @Override
    public JobExecution startJob(JobName jobName) {
        Job job  = jobRepository.read(jobName);
        return null;
    }


}
