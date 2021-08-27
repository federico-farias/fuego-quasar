package com.starwars.quasar.domain.services;

import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;

/**
 * Clase que se encarga de buscar un satélite.
 * 
 * @author Federico Farias Sánchez
 *
 */
public class SateliteFinder {

	private final SatelliteRepository repository;

	/**
	 * @param repository Repositorio de acceso a los satélites.
	 */
	public SateliteFinder(SatelliteRepository repository) {
		this.repository = repository;
	}

	/**
	 * Realiza la búsqueda de un satélite por nombre, si este no se encuentra lanza una excepción.
	 * 
	 * @param sateliteName Nombre del satélite.
	 * @return Satélite encontrado.
	 */
	public Satelite findByName(String sateliteName) {
		Satelite satelite = this.repository.findByName(sateliteName);
		if (satelite == null) {
			throw new BusinessException(String.format("No se encontró el satelite [%s].", sateliteName));
		}
		return satelite;
	}

}
