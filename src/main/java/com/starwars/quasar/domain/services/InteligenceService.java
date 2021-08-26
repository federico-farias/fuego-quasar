package com.starwars.quasar.domain.services;

import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.request.DistressMessageRequest;

public interface InteligenceService {

	/**
	 * 
	 * @param request Mensaje de auxilio proveniente de la nave Fuego de Quasar.
	 * @return Ubicación y mensaje decifrado.
	 */
	TopSecretHttpResponse decipher(DistressMessageRequest request);

}
