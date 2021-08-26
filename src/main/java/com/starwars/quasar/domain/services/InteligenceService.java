package com.starwars.quasar.domain.services;

import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.request.SecretManifestoRequest;

public interface InteligenceService {
	
	TopSecretHttpResponse detect(SecretManifestoRequest request);

}
