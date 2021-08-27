package com.starwars.quasar.domain.services;

import java.util.LinkedList;
import java.util.List;

import com.starwars.quasar.application.usecase.SatelliteRequest;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;

/**
 * Clase que se encarga de buscar un satélite.
 * 
 * @author Federico Farias Sánchez
 *
 */
public class SatellitesFinder {

	private SateliteFinder finder;

	/**
	 * @param setelliteRepository Repositorio de acceso a los satélites.
	 */
	public SatellitesFinder(SatelliteRepository setelliteRepository) {
		this.finder = new SateliteFinder(setelliteRepository);
	}
	
	/**
	 * Método para buscar satélites por nombre, si no se encuentra alguno se arroja una excepción.
	 * 
	 * @param satellitesRequest Información de los satélites a buscar.
	 * @return Lista de satelites encontrados.
	 */
	public List<Satelite> findByName(List<SatelliteRequest> satellitesRequest) {
		List<Satelite> satellitesFound = new LinkedList<>();
		for (SatelliteRequest sateliteRequest: satellitesRequest) {
			Satelite satelite = this.finder.findByName(sateliteRequest.getName());
			satellitesFound.add(satelite);
		}
		return satellitesFound;
	}

}
