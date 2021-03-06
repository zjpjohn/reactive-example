package com.sf.util.datasource;

import com.sf.util.file.SFClassUtils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class TruncateUtil {

    private final JdbcTemplate jdbcTemplate;

    private List<String> ddlList = Arrays.asList(
            "001_create_table_deltaRun.ddl",
            "002_create_table_deltaRecord.ddl",
            "003_create_table_job.ddl",
            "004_create_table_jobExecution.ddl");

    private List<String> tableList = Arrays.asList("job","jobExecution","deltaRun","deltaRecord");

    public TruncateUtil(DataSource  dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void truncateAllTables() {
        this.tableList.forEach(this::truncateTable);
    }

    public void truncateTables(String ... tables) {
        Arrays.asList(tables).forEach(this::truncateTable);
    }

    public void truncateTable(String table) {
        this.jdbcTemplate.execute(new SQL().DELETE_FROM(table).toString());
    }

    public void executeAllDDL(){
        this.ddlList.forEach(this::executeDDL);
    }

    public void dropAllTables(){
        this.tableList.forEach(this::dropTable);
    }

    public void dropTable(String table){
        this.jdbcTemplate.execute("DROP TABLE " + table);
    }

    public void executeDDL(String ddlFile)  {
        String fileToString = getDDLString(ddlFile);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.simpleDatSource());
        jdbcTemplate.execute(fileToString);
    }

    private String getDDLString(String ddlFile) {
        URL resource = SFClassUtils.getClassLoader().getResource(ddlFile);
        String ddlFileString = null;
        try {
            ddlFileString = FileUtils.readFileToString(new File(resource.toURI()));
        } catch (IOException | URISyntaxException  | NullPointerException e ) {
            throw new RuntimeException("Unable to find [" + ddlFile + "]",e);
        }
        return ddlFileString;
    }
}
