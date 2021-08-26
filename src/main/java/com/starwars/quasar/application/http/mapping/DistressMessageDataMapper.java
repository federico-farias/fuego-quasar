package com.starwars.quasar.application.http.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.DistressMessageHttpRequest;
import com.starwars.quasar.application.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.domain.request.SatelliteRequest;

@Service
public class DistressMessageDataMapper {

	public List<SatelliteRequest> toRequest(DistressMessageHttpRequest request) {
		return request
				.getSatellites()
				.stream()
				.map(this::toRequest)
				.collect(Collectors.toList());
	}

	public SatelliteRequest toRequest(SatelliteHttpRequest satelite) {
		return new SatelliteRequest(
				satelite.getName(), 
				satelite.getDistance(), 
				satelite.getMessage());
	}

}
