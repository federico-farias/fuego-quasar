package com.starwars.quasar.domain.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.starwars.quasar.domain.model.KenobiSatelite;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.model.SatoSatelite;
import com.starwars.quasar.domain.model.SkywalkerSatelite;

@Repository
public class SatelliteRepositoryImpl implements SatelliteRepository {

	private final Map<String, Satelite> satellitesMap;
	
	private final List<Satelite> satellitesList;

	public SatelliteRepositoryImpl() {
		this.satellitesMap = new LinkedHashMap<>();
		KenobiSatelite kenobi = new KenobiSatelite();
		this.satellitesMap.put(kenobi.getName().toLowerCase(), kenobi);

		SkywalkerSatelite skywalker = new SkywalkerSatelite();
		this.satellitesMap.put(skywalker.getName().toLowerCase(), skywalker);

		SatoSatelite sato = new SatoSatelite();
		this.satellitesMap.put(sato.getName().toLowerCase(), sato);
		
		this.satellitesList = this.satellitesMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Satelite findByName(String name) {
		if (!this.satellitesMap.containsKey(name.trim().toLowerCase())) {
			return null;
		}
		return this.satellitesMap.get(name.trim().toLowerCase());
	}

	@Override
	public List<Satelite> findAll() {
		return satellitesList;
	}

}
