package com.sf.delta.repository;

import com.sf.delta.domain.Job;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class JobJdbcRepository implements JobRepository {

    private SimpleJdbcInsert insertJob;

    public JobJdbcRepository(DataSource dataSource) {
        this.insertJob = new SimpleJdbcInsert(dataSource)
                .withTableName("job")
                .usingGeneratedKeyColumns("jobId");
    }

    public void init(){
        this.insertJob.compile();
    }

    @Override
    public Job create(String jobName, String dataKey) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("jobName", jobName);
        parameters.put("dataKey", dataKey);
        Number newId = insertJob.executeAndReturnKey(parameters);
        return new Job(newId.longValue(), jobName, dataKey);
    }

}
