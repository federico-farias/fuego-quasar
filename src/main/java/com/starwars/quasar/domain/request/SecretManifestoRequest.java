package com.starwars.quasar.domain.request;

import java.util.List;

import com.starwars.quasar.domain.exceptions.BusinessException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SecretManifestoRequest {

	private List<SatelliteRequest> satellites;

	public SecretManifestoRequest(List<SatelliteRequest> satellites) {
		if (satellites == null || satellites.size() < 3) {
			throw new BusinessException("No se encontraron satelites suficientes.");
		}
		this.satellites = satellites;
	}

}
