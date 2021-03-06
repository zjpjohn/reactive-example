package com.sf.job;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.definition.JobCollectorDefinitionBuilder;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.item.impl.JsonRecordItemCollectorProcessor;
import com.sf.job.item.impl.JsonRecordItemReader;
import com.sf.job.item.impl.JsonRecordItemWriter;
import com.sf.job.observer.FileObserver;
import com.sf.util.file.FileUtil;

import java.util.Map;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class TestJobFactory {


    public static JobCollectorDefinition<String, JsonRecord> getTestJob(JobType jobType,
                                                                        IdKey idKey,
                                                                        String fileName,
                                                                        Map<String,Integer> parameterMap) {
        return JobCollectorDefinitionBuilder.<String,JsonRecord>jobDefinition()
                .jobType(jobType)
                .idKey(idKey)
                .observableItems(new FileObserver().createObserver(FileUtil.getClassPathInputStream(fileName)))
                .itemReader(new JsonRecordItemReader(idKey))
                .itemProcessor(new JsonRecordItemCollectorProcessor())
                .itemWriter(new JsonRecordItemWriter())
                .jobExecutionParameters(parameterMap)
                .build();
    }
}
