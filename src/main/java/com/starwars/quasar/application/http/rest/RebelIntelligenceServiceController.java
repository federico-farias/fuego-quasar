package com.starwars.quasar.application.http.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.application.http.mapping.DistressMessageDataMapper;
import com.starwars.quasar.application.http.mapping.TopSecretDataMapper;
import com.starwars.quasar.application.http.schema.DistressMessageHttpRequest;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.model.DecryptedMessageResponse;
import com.starwars.quasar.domain.request.DistressMessageRequest;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.services.InteligenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fuego de Quasar - Top Secret")
@RestController
@RequestMapping("/topsecret")
public class RebelIntelligenceServiceController {

	private final InteligenceService service;
	
	private final TopSecretDataMapper responseDataMapper;
	
	private final DistressMessageDataMapper requestDataMapper;

	public RebelIntelligenceServiceController(
			InteligenceService service, 
			TopSecretDataMapper dataMapper,
			DistressMessageDataMapper requestDataMapper) {
		this.service = service;
		this.responseDataMapper = dataMapper;
		this.requestDataMapper = requestDataMapper;
	}

	@Operation(summary = "Retorna la fuente y el mensaje descifrado proveniente de la nave.")
	@PostMapping
	public TopSecretHttpResponse decipher(@RequestBody DistressMessageHttpRequest request) {
		List<SatelliteRequest> satellites = this.requestDataMapper.toRequest(request);
		DecryptedMessageResponse response = this.service.decipher(new DistressMessageRequest(satellites));
		return this.responseDataMapper.toHttpResponse(response);
	}

}