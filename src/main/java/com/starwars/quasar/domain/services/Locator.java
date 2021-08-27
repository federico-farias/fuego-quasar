package com.starwars.quasar.domain.services;

import com.starwars.quasar.domain.model.Position;

/**
 * Contrato con la firma sugerida para implementar el cálculo de la posición de
 * la nave.
 * 
 * @author Federico Farias Sánchez
 *
 */
public interface Locator {

	/**
	 * 
	 * @param distances Distancia al emisor tal cual se recibe en cada satélite.
	 * @return Las coordenadas 'x' e 'y' del emisor del mensaje.
	 */
	Position getLocation(double... distances);

}
