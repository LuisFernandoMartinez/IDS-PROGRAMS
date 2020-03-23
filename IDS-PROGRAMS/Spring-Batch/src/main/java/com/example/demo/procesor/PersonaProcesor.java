package com.example.demo.procesor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.models.*;


public class PersonaProcesor implements ItemProcessor<Entity,Entity> {
	
	private static final Logger LOG= LoggerFactory.getLogger(PersonaProcesor.class);

	@Override
	public Entity process(Entity item) throws Exception {

		String primerNombre=item.getpNombre().toUpperCase();
		String segundoNombre=item.getsNombre().toUpperCase();
		String tel=item.getPhone();
		
		Entity entidad1=new Entity(primerNombre,segundoNombre,tel);
		LOG.info("Convirtiendo ("+item+") a ("+ entidad1+")");
		return entidad1;
	}
	
	
	
	

}
