package com.starwars.quasar.application.http.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.application.http.mapping.DistressMessageDataMapper;
import com.starwars.quasar.application.http.mapping.TopSecretDataMapper;
import com.starwars.quasar.application.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.application.usecase.DistressMessageRequest;
import com.starwars.quasar.application.usecase.GetDistressMessageUseCase;
import com.starwars.quasar.application.usecase.SatelliteRequest;
import com.starwars.quasar.domain.model.DecryptedMessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fuego de Quasar - Top Secret Split")
@RestController
@RequestMapping("/topsecret_split")
public class RebelIntelligenceSplitServiceController {
	
	private final List<SatelliteRequest> satellites;

	private final GetDistressMessageUseCase service;
	
	private final TopSecretDataMapper responseDataMapper;
	
	private final DistressMessageDataMapper requestDataMapper;

	public RebelIntelligenceSplitServiceController(
			GetDistressMessageUseCase service, 
			TopSecretDataMapper responseDataMapper,
			DistressMessageDataMapper requestDataMapper) {
		this.service = service;
		this.responseDataMapper = responseDataMapper;
		this.requestDataMapper = requestDataMapper;
		this.satellites = new LinkedList<>();
	}

	@Operation(summary = "Recibe información de la nave.")
	@PostMapping("/{satelite-name}")
	public void detect(@PathVariable("satelite-name") String sateliteName, @RequestBody SatelliteHttpRequest request) {
		SatelliteRequest satelite = this.requestDataMapper.toRequest(request);
		this.satellites.add(satelite);
	}
	
	@Operation(summary = "Retorna la fuente y el mensaje descifrado proveniente de la nave.")
	@GetMapping
	public TopSecretHttpResponse detect() {
		DecryptedMessageResponse response = this.service.execute(new DistressMessageRequest(satellites));
		this.satellites.clear();
		return this.responseDataMapper.toHttpResponse(response);
	}

}
