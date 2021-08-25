package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.application.http.schema.PositionHttpResponse;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.request.SecretManifestoRequest;
import com.starwars.quasar.domain.services.InteligenceServiceImpl;
import com.starwars.quasar.domain.services.Locator;
import com.starwars.quasar.domain.services.MessageDecryptor;

public class InteligenceServiceTest {

	private InteligenceServiceImpl service;
	
	@BeforeEach
	public void setUp() {
		this.service = new InteligenceServiceImpl(
				new Locator(), 
				new MessageDecryptor());
	}

	@Test
	public void topSecreetTest() {
		TopSecretHttpResponse expectedResponse = new TopSecretHttpResponse(
				new PositionHttpResponse(-100.04391951008549, 75.45636245570131), 
				"este es un mensaje secreto");
		
		SecretManifestoRequest request = new SecretManifestoRequest(
				Arrays.asList(
						new SatelliteRequest("kenobi", 505.0, new String[] { "este", "", "", "mensaje", "" }),
						new SatelliteRequest("skywalker", 253.0, new String[] { "", "es", "", "", "secreto" }),
						new SatelliteRequest("sato", 628.0, new String[] { "este", "", "un", "", "" })
				));

		TopSecretHttpResponse responseResult = this.service.detect(request);
		assertEquals(responseResult, expectedResponse);
	}

}
