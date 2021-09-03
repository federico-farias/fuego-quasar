package com.starwars.quasar.domain.services;

import java.util.List;
import java.util.stream.Collectors;

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
		return satellitesRequest
				.stream()
				.map(satelite -> this.finder.findByName(satelite.getName()))
				.collect(Collectors.toList());
	}

}
