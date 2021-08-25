package com.starwars.quasar.http.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.http.schema.TopSecretHttpRequest;
import com.starwars.quasar.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.services.InteligenceServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Productos")
@RestController
@RequestMapping("/topsecret")
public class TopSecretController {

	private final InteligenceServiceImpl service;

	public TopSecretController(InteligenceServiceImpl service) {
		this.service = service;
	}

	@Operation(summary = "Obtiene la posición de la nave.")
	@PostMapping
	public TopSecretHttpResponse topSecret(@RequestBody TopSecretHttpRequest request) {
		return this.service.topSecreet(request);
	}

}
