package com.starwars.quasar.domain.services;

import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;

public class SateliteFinder {

	private final SatelliteRepository repository;

	public SateliteFinder(SatelliteRepository repository) {
		this.repository = repository;
	}

	public Satelite findByName(String sateliteName) {
		Satelite satelite = this.repository.findByName(sateliteName);
		if (satelite == null) {
			throw new BusinessException(String.format("No se encontró el satelite [%s].", sateliteName));
		}
		return satelite;
	}

}
