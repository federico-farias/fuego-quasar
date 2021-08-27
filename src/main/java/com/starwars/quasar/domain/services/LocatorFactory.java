package com.starwars.quasar.domain.services;

import java.util.List;

import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.infrastructure.LocatorImpl;

/**
 * Clase que se encarga de ocultar el acoplamiento de la clase que implementa la
 * interfaz Locator.
 * 
 * @author Federico Farias Sánchez
 *
 */
public final class LocatorFactory {

	private LocatorFactory() {
		super();
	}

	/**
	 * Método de fábrica para crear una instancia de Locator.
	 * 
	 * @param satellitesPositions
	 * @return
	 */
	public static Locator create(List<Position> satellitesPositions) {
		return new LocatorImpl(satellitesPositions);
	}

}
