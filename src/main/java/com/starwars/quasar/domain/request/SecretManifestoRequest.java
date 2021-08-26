package com.starwars.quasar.domain.request;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SecretManifestoRequest {

	private List<SatelliteRequest> satellites;

	public SecretManifestoRequest(List<SatelliteRequest> satellites) {
		this.satellites = satellites;
	}

}
