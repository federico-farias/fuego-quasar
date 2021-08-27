package com.starwars.quasar.domain.repository;

import java.util.List;

import com.starwars.quasar.domain.model.Satelite;

/**
 * Contrato para acceder a los satélites.
 * 
 * @author Federico Farias Sánchez
 *
 */
public interface SatelliteRepository {

	/**
	 * Método para buscar un satélite por nombre.
	 * 
	 * @param name Nombre del satélite a buscar.
	 * @return Satélite encontrado, obtiene null en caso de no encontrarlo.
	 */
	Satelite findByName(String name);

	/**
	 * Obtiene todos los satelites.
	 * 
	 * @return Lista de satélites encontrados.
	 */
	List<Satelite> findAll();

}
