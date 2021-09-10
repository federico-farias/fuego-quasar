package com.starwars.quasar.application.http.mapping;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.PositionHttpResponse;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.application.usecase.DecryptedMessageResponse;

/**
 * Clase que se encarga de mapear las entradas los puertos para transformarlos a las entradas de negocio.
 * @author Federico Farias Sánchez
 *
 */
@Service
public class TopSecretDataMapper {

	/**
	 * @param request Entrada del puerto 
	 * @return Entrada de negocio
	 */
	public TopSecretHttpResponse toHttpResponse(DecryptedMessageResponse response) {
		return new TopSecretHttpResponse(
				new PositionHttpResponse(
						response.getPosition().getX(), 
						response.getPosition().getY()), 
				response.getMessage());
	}

}
