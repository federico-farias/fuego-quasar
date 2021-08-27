package com.starwars.quasar.application.usecase;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DistressMessageRequest {

	private List<SatelliteRequest> satellites;

	public DistressMessageRequest(List<SatelliteRequest> satellites) {
		this.satellites = satellites;
	}

}
