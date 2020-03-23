package com.example.demo;

import javax.batch.api.listener.JobListener;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.models.Entity;
import com.example.demo.procesor.PersonaProcesor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilder;
	
	@Autowired
	public StepBuilderFactory stepBuilder;
	
	public FlatFileItemReader<Entity> reader(){
		return new FlatFileItemReaderBuilder<Entity>()
				.name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[] {"pNombre","sNombre","phone"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Entity>() {{
					setTargetType(Entity.class);
				}})
				.build();			
	}
	
	@Bean
	public PersonaProcesor processor() {
		return new PersonaProcesor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Entity> writer(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Entity>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO persona (primer_nombre,segundo_nombre,telefono) VALUES (:pNombre,:sNombre,:phone)")
                .dataSource(dataSource)
                .build();
	}
	
	@Bean
	public Job importPersonaJob(JobListener listener,Step step1) {
		return jobBuilder.get("importPersonaJob")
				.incrementer(new RunIdIncrementer())
				.listener((JobExecutionListener) listener)
				.flow(step1)
				.end()
				.build();
	}
    
	@Bean
	public Step step1(JdbcBatchItemWriter<Entity> writer) {
		return stepBuilder.get("step1")
				.<Entity,Entity> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}
}
