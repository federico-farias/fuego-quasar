package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.model.DecryptedMessageResponse;
import com.starwars.quasar.domain.model.PositionResponse;
import com.starwars.quasar.domain.repository.SatelliteRepositoryImpl;
import com.starwars.quasar.domain.request.DistressMessageRequest;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.services.impl.InteligenceServiceImpl;
import com.starwars.quasar.domain.services.impl.MessageDecryptorImpl;

public class InteligenceServiceTest {

	private InteligenceServiceImpl service;
	
	@BeforeEach
	public void setUp() {
		this.service = new InteligenceServiceImpl(
				new SatelliteRepositoryImpl(), 
				new MessageDecryptorImpl());
	}

	@Test
	public void itShouldGetTheLocationAndDecipherTheShipsDistressMessage() {
		DecryptedMessageResponse expectedResponse = new DecryptedMessageResponse(
				new PositionResponse(-100.04391951008549, 75.45636245570131), 
				"este es un mensaje secreto");
		
		DistressMessageRequest request = new DistressMessageRequest(
				Arrays.asList(
						new SatelliteRequest("kenobi", 505.0, new String[] { "este", "", "", "mensaje", "" }),
						new SatelliteRequest("skywalker", 253.0, new String[] { "", "es", "", "", "secreto" }),
						new SatelliteRequest("sato", 628.0, new String[] { "este", "", "un", "", "" })
				));

		DecryptedMessageResponse responseResult = this.service.decipher(request);
		assertEquals(responseResult, expectedResponse);
	}

}
