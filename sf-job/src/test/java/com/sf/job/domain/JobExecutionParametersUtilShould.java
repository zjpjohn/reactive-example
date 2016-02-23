package com.sf.job.domain;

import com.sf.job.testjob.DefaultJobExecutionParameters;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionParametersUtilShould {


    @Test
    public void createMapStrings() {
        Map<String, Integer> stringStringMap = JobExecutionParametersUtil.jobExecutionParametersMap(DefaultJobExecutionParameters.values());
        MatcherAssert.assertThat(stringStringMap.size(), CoreMatchers.equalTo(10));


    }

}