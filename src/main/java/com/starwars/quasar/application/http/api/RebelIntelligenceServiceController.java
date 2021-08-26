package com.starwars.quasar.application.http.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.application.http.schema.SecretManifestoHttpRequest;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.request.SecretManifestoRequest;
import com.starwars.quasar.domain.services.InteligenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fuego de Quasar")
@RestController
@RequestMapping("/topsecret")
public class RebelIntelligenceServiceController {

	private final InteligenceService service;

	public RebelIntelligenceServiceController(InteligenceService service) {
		this.service = service;
	}

	@Operation(summary = "Obtiene la posición de la nave.")
	@PostMapping
	public TopSecretHttpResponse detect(@RequestBody SecretManifestoHttpRequest request) {
		List<SatelliteRequest> satellites = request.getSatellites().stream().map(satelite -> {
			return new SatelliteRequest(satelite.getName(), satelite.getDistance(), satelite.getMessage());
		}).collect(Collectors.toList());
		return this.service.detect(new SecretManifestoRequest(satellites));
	}

}
