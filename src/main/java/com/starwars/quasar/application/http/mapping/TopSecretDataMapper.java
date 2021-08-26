package com.starwars.quasar.application.http.mapping;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.PositionHttpResponse;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.model.DecryptedMessageResponse;

@Service
public class TopSecretDataMapper {

	public TopSecretHttpResponse toHttpResponse(DecryptedMessageResponse response) {
		return new TopSecretHttpResponse(
				new PositionHttpResponse(
						response.getPosition().getX(), 
						response.getPosition().getY()), 
				response.getMessage());
	}

}
