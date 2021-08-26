package com.starwars.quasar.domain.services;

import java.util.LinkedList;
import java.util.List;

import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;
import com.starwars.quasar.domain.request.SatelliteRequest;

public class SatellitesFinder {

	private SateliteFinder finder;

	public SatellitesFinder(SatelliteRepository setelliteRepository) {
		this.finder = new SateliteFinder(setelliteRepository);
	}
	
	public List<Satelite> findByName(List<SatelliteRequest> satellitesRequest) {
		List<Satelite> satellitesFound = new LinkedList<>();
		for (SatelliteRequest sateliteRequest: satellitesRequest) {
			Satelite satelite = this.finder.findByName(sateliteRequest.getName());
			satellitesFound.add(satelite);
		}
		return satellitesFound;
	}

}
