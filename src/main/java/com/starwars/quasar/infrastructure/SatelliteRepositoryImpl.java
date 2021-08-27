package com.starwars.quasar.infrastructure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;

/**
 * Contrato para acceder a los satélites.
 * 
 * @author Federico Farias Sánchez
 *
 */
@Repository
public class SatelliteRepositoryImpl implements SatelliteRepository {

	private final Map<String, Satelite> satellitesMap;
	
	private final List<Satelite> satellitesList;

	public SatelliteRepositoryImpl() {
		this.satellitesMap = new LinkedHashMap<>();
		this.satellitesMap.put("kenobi", new Satelite("kenobi", new Position(-500.0, -200.0)));
		this.satellitesMap.put("skywalker", new Satelite("skywalker", new Position(100.0, -100.0)));
		this.satellitesMap.put("sato", new Satelite("sato", new Position(500.0, 100.0)));
		this.satellitesList = this.satellitesMap.values().stream().collect(Collectors.toList());
	}

	/**
	 * Método para buscar un satélite por nombre.
	 * 
	 * @param name Nombre del satélite a buscar.
	 * @return Satélite encontrado, obtiene null en caso de no encontrarlo.
	 */
	@Override
	public Satelite findByName(String name) {
		if (!this.satellitesMap.containsKey(name.trim().toLowerCase())) {
			return null;
		}
		return this.satellitesMap.get(name.trim().toLowerCase());
	}

	/**
	 * Obtiene todos los satelites.
	 * 
	 * @return Lista de satélites encontrados.
	 */
	@Override
	public List<Satelite> findAll() {
		return satellitesList;
	}

}
