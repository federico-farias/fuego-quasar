package com.starwars.quasar.domain.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.PositionHttpResponse;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.request.DistressMessageRequest;
import com.starwars.quasar.domain.services.InteligenceService;
import com.starwars.quasar.domain.services.Locator;
import com.starwars.quasar.domain.services.MessageDecryptor;
import com.starwars.quasar.domain.services.SatellitesFinder;

@Service
public class InteligenceServiceImpl implements InteligenceService {

	private final MessageDecryptor decryptor;
	
	private final SatellitesFinder satellitesFinder;

	public InteligenceServiceImpl(SatelliteRepository setelliteRepository, MessageDecryptor decryptor) {
		this.satellitesFinder = new SatellitesFinder(setelliteRepository);
		this.decryptor = decryptor;
	}

	@Override
	public TopSecretHttpResponse decipher(DistressMessageRequest request) {
		List<SatelliteRequest> satellitesRequest = request.getSatellites();
		
		List<Satelite> satellites = this.satellitesFinder.findByName(satellitesRequest);
		List<Position> satellitesPositions = getPositions(satellites);
		Locator locator = new LocatorImpl(satellitesPositions);
		
		String[][] messages = getMessages(satellitesRequest);
		double[] distances = getDistances(satellitesRequest);

		Position position = locator.getLocation(distances);
		String message = this.decryptor.getMessage(messages);

		return new TopSecretHttpResponse(new PositionHttpResponse(position.getX(), position.getY()), message);
	}

	private List<Position> getPositions(List<Satelite> satellites) {
		return satellites
				.stream()
				.map(satelite -> satelite.getPosition())
				.collect(Collectors.toList());
	}

	private double[] getDistances(List<SatelliteRequest> satellitesRequest) {
		double[] distances = new double[satellitesRequest.size()];
		for (int i = 0; i < satellitesRequest.size(); i++) {
			SatelliteRequest satelite = satellitesRequest.get(i);
			distances[i] = satelite.getDistance().doubleValue();
		}
		return distances;
	}

	private String[][] getMessages(List<SatelliteRequest> satellitesRequest) {
		String[][] messages = new String[satellitesRequest.size()][];
		for (int i = 0; i < satellitesRequest.size(); i++) {
			SatelliteRequest satelite = satellitesRequest.get(i);
			messages[i] = satelite.getMessage();
		}
		return messages;
	}

}
