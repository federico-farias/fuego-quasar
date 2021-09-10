package com.starwars.quasar.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;

public class PositionsDataMapper {

	/**
	 * Método para las posiciones obtenidas de los satélites.
	 * 
	 * @param satellites Satélites de los cuales se obtiene su posición.
	 * @return Lista de posiciones de los satélites.
	 */
	public List<Position> getPositions(List<Satelite> satellites) {
		return satellites
				.stream()
				.map(satelite -> satelite.getPosition())
				.collect(Collectors.toList());
	}

}
