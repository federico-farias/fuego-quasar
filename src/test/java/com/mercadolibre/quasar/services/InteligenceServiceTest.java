package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.application.usecase.DecryptedMessageResponse;
import com.starwars.quasar.application.usecase.DistressMessageRequest;
import com.starwars.quasar.application.usecase.GetDistressMessageUseCase;
import com.starwars.quasar.application.usecase.DecryptedPositionResponse;
import com.starwars.quasar.application.usecase.SatelliteRequest;
import com.starwars.quasar.infrastructure.MessageDecryptorImpl;
import com.starwars.quasar.infrastructure.SatelliteRepositoryImpl;

public class InteligenceServiceTest {

	private GetDistressMessageUseCase service;
	
	@BeforeEach
	public void setUp() {
		this.service = new GetDistressMessageUseCase(
				new SatelliteRepositoryImpl(), 
				new MessageDecryptorImpl());
	}

	@Test
	public void itShouldGetTheLocationAndDecipherTheShipsDistressMessage() {
		DecryptedMessageResponse expectedResponse = new DecryptedMessageResponse(
				new DecryptedPositionResponse(-100.04391951008549, 75.45636245570131), 
				"este es un mensaje secreto");
		
		DistressMessageRequest request = new DistressMessageRequest(
				Arrays.asList(
						new SatelliteRequest("kenobi", 505.0, new String[] { "este", "", "", "mensaje", "" }),
						new SatelliteRequest("skywalker", 253.0, new String[] { "", "es", "", "", "secreto" }),
						new SatelliteRequest("sato", 628.0, new String[] { "este", "", "un", "", "" })
				));

		DecryptedMessageResponse responseResult = this.service.execute(request);
		assertEquals(responseResult, expectedResponse);
	}

}
