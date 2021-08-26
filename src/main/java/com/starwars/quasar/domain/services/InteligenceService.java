package com.starwars.quasar.domain.services;

import com.starwars.quasar.domain.model.DecryptedMessageResponse;
import com.starwars.quasar.domain.request.DistressMessageRequest;

public interface InteligenceService {

	/**
	 * 
	 * @param request Mensaje de auxilio proveniente de la nave Fuego de Quasar.
	 * @return Ubicación y mensaje decifrado.
	 */
	DecryptedMessageResponse decipher(DistressMessageRequest request);

}
