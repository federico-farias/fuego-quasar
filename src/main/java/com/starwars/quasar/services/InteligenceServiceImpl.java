package com.starwars.quasar.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.http.schema.PositionHttpResponse;
import com.starwars.quasar.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.http.schema.TopSecretHttpRequest;
import com.starwars.quasar.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.model.KenobiSatelite;
import com.starwars.quasar.model.Position;
import com.starwars.quasar.model.Satelite;
import com.starwars.quasar.model.SatoSatelite;
import com.starwars.quasar.model.SkywalkerSatelite;

@Service
public class InteligenceServiceImpl implements InteligenceService {

	private Map<String, Satelite> satellites;
	private MessageDecryptor decryptor;
	private Locator locator;

	public InteligenceServiceImpl(Locator locator, MessageDecryptor decryptor) {
		this.locator = locator;
		this.decryptor = decryptor;

		this.satellites = new LinkedHashMap<>();
		KenobiSatelite kenobi = new KenobiSatelite();
		this.satellites.put(kenobi.getName().toLowerCase(), kenobi);

		SkywalkerSatelite skywalker = new SkywalkerSatelite();
		this.satellites.put(skywalker.getName().toLowerCase(), skywalker);

		SatoSatelite sato = new SatoSatelite();
		this.satellites.put(sato.getName().toLowerCase(), sato);
	}

	public TopSecretHttpResponse topSecreet(TopSecretHttpRequest request) {
		List<SatelliteHttpRequest> satellites = request.getSatellites();
		String[][] messages = new String[satellites.size()][];
		double[] distances = new double[satellites.size()];
		
		List<Position> satellitesPositions = this.satellites
				.values()
				.stream()
				.map(satelite -> satelite.getPosition())
				.collect(Collectors.toList());
		
		for (int i = 0; i < satellites.size(); i++) {
			SatelliteHttpRequest satelite = satellites.get(i);
			distances[i] = satelite.getDistance().doubleValue();
			messages[i] = satelite.getMessage();
		}
		
		Position position = this.locator.getLocation(satellitesPositions, distances);
		String message = this.decryptor.decipher(messages);
		
		return new TopSecretHttpResponse(
				new PositionHttpResponse(
						position.getX(), 
						position.getY()), 
				message);
	}

	@Override
	public String getMessage(String[]... messages) {
		return this.decryptor.decipher(messages);
	}

	@Override
	public Position getLocation(double... distances) {
		List<Position> positions = this.satellites.values().stream().map(satelite -> satelite.getPosition())
				.collect(Collectors.toList());
		return this.locator.getLocation(positions, distances);
	}

}
