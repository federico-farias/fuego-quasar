package com.starwars.quasar.application.http.api;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.application.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.request.SecretManifestoRequest;
import com.starwars.quasar.domain.services.InteligenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fuego de Quasar")
@RestController
@RequestMapping("/topsecret_split")
public class RebelIntelligenceSplitServiceController {
	
	List<SatelliteRequest> satellites;

	private final InteligenceService service;

	public RebelIntelligenceSplitServiceController(InteligenceService service) {
		this.service = service;
		this.satellites = new LinkedList<>();
	}

	@Operation(summary = "Recibe información de la nave.")
	@PostMapping("/{satelite-name}")
	public void detect(@PathVariable("satelite-name") String sateliteName, @RequestBody SatelliteHttpRequest request) {
		this.satellites.add(new SatelliteRequest(
				request.getName(), 
				request.getDistance(), 
				request.getMessage()));
	}
	
	@Operation(summary = "Retorna la fuente y el mensaje auxilio descifrado de la nave.")
	@GetMapping
	public TopSecretHttpResponse detect() {
		TopSecretHttpResponse response = this.service.detect(new SecretManifestoRequest(satellites));
		this.satellites = new LinkedList<>();
		return response;
	}

}
