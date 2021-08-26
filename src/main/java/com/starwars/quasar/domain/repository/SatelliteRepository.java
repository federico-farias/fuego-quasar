package com.starwars.quasar.domain.repository;

import java.util.List;

import com.starwars.quasar.domain.model.Satelite;

public interface SatelliteRepository {
	
	Satelite findByName(String name);

	List<Satelite> findAll();

}
