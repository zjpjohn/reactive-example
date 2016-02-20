package com.sf.job.service;

import com.sf.job.JobDefinition;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;

/**
 * Created by adityasofat on 20/02/2016.
 */
public interface JobService {

    Job addJob(JobDefinition jobDefinition);

    Job getJob(JobName jobName);
}