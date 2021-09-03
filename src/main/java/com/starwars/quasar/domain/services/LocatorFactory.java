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

	/**
	 * Singleton de locator.
	 */
	private static LocatorImpl locator;

	/**
	 * Constructor predeterminado privado para prevenir crear instancia de la
	 * factoría.
	 */
	private LocatorFactory() {
		super();
	}

	/**
	 * Método de fábrica para crear una instancia de Locator.
	 * 
	 * @param positions
	 * @return
	 */
	public static Locator getInstance(List<Position> positions) {
		if (locator == null) {
			locator = new LocatorImpl();
		}
		locator.setPositions(positions);
		return locator;
	}

}
