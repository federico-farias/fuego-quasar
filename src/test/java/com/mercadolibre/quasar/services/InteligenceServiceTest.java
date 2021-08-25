package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.http.schema.PositionHttpResponse;
import com.starwars.quasar.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.http.schema.TopSecretHttpRequest;
import com.starwars.quasar.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.services.InteligenceServiceImpl;
import com.starwars.quasar.services.Locator;
import com.starwars.quasar.services.MessageDecryptor;

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
		
		TopSecretHttpRequest request = new TopSecretHttpRequest(
				Arrays.asList(
						new SatelliteHttpRequest("kenobi", 505.0, new String[] { "este", "", "", "mensaje", "" }),
						new SatelliteHttpRequest("skywalker", 253.0, new String[] { "", "es", "", "", "secreto" }),
						new SatelliteHttpRequest("sato", 628.0, new String[] { "este", "", "un", "", "" })
				));

		TopSecretHttpResponse responseResult = this.service.topSecreet(request);
		assertEquals(responseResult, expectedResponse);
	}

}
