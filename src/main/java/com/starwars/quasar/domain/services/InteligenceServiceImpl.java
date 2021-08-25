package com.starwars.quasar.domain.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.PositionHttpResponse;
import com.starwars.quasar.application.http.schema.TopSecretHttpResponse;
import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.model.KenobiSatelite;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.model.SatoSatelite;
import com.starwars.quasar.domain.model.SkywalkerSatelite;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.request.SecretManifestoRequest;

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

	public TopSecretHttpResponse detect(SecretManifestoRequest request) {
		List<SatelliteRequest> satellites = request.getSatellites();
		String[][] messages = new String[satellites.size()][];
		double[] distances = new double[satellites.size()];
		
		ensurer(request.getSatellites());
		
		List<Position> satellitesPositions = this.satellites
				.values()
				.stream()
				.map(satelite -> satelite.getPosition())
				.collect(Collectors.toList());
		
		for (int i = 0; i < satellites.size(); i++) {
			SatelliteRequest satelite = satellites.get(i);
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

	private void ensurer(List<SatelliteRequest> satellites) {
		for (SatelliteRequest satelite : satellites) {
			if (!this.satellites.containsKey(satelite.getName().toLowerCase())) {
				throw new BusinessException(String.format("No se encontró el satelite [%s]", satelite.getName()));
			}
		}
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
