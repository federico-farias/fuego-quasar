package com.starwars.quasar.application.http.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "Operación Fuego de Quasar", 
		contact = @Contact(
				name = "Federico Farías Sánchez",
				email = "federico.farias@outlook.com"),
		description = "Satelites disponibles: <br /> "
				+ "●Kenobi: [-500, -200]<br />"
				+ "●Skywalker: [100, -100]<br/>"
				+ "●Sato: [500, 100]", version = "0.1"))
public class OpenApiDefinitionConfig {

}
