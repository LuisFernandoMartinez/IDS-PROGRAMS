package com.example.demo.listener;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.models.Entity;

@Component
public class JobListener extends JobExecutionListenerSupport {

	private static final Logger LOG=LoggerFactory.getLogger(JobListener.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getBatchStatus() == BatchStatus.COMPLETED ) {
			LOG.info("FINALIZO TRABAJO REVISA RESUKLTADOS");
			
			jdbcTemplate.query("SELECT primer_nombre,segundo_nombre,telefono FROM persona", (rs,row)->new Entity(rs.getString(1),
					           rs.getString(2),rs.getString(3)))
			            .forEach(entity -> LOG.info("Registro < " + entity + " >"));
		}
	}
	
	
	
	
}
