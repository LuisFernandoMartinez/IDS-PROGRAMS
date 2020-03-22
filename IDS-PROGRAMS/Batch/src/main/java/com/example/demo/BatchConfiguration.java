package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
	
	

}
