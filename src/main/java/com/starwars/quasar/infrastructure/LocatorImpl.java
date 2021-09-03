package com.starwars.quasar.infrastructure;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starwars.quasar.domain.exceptions.LocationException;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.services.Locator;

/**
 * Clase que implementa la firma sugerida para calcular la posición de la nave.
 * 
 * @author Federico Farias Sánchez
 *
 */
public class LocatorImpl implements Locator {

	private List<Position> positionsList;

	/**
	 * Constructor que recibe como argumentos una lista de posiciones 
	 * (requerido para el cálculo de la posición de la nave). La lista 
	 * de posiciones se recibe por constructor para lograr respetar la 
	 * firma sugerida dado dicha firma recibe únicamente las distancias.
	 *  
	 * @param positionsList Lista de posiciones
	 */
	public LocatorImpl(List<Position> positionsList) {
		this.positionsList = positionsList;
	}
	
	/**
	 * Constructor predeterminado para poder setear las pociciones despues de crear una instancia.
	 */
	public LocatorImpl() {
		super();
	}
	
	/**
	 * Método para recibir posiciones.
	 * 
	 * @param positions
	 */
	public void setPositions(List<Position> positions) {
		if (positions == null) {
			throw new LocationException("Las posiciones son requeridas.");
		}
		this.positionsList = positions;
	}

	/**
	 * Método que implementa la firma sugerida para calcular la posición de la nave.
	 */
	@Override
	public Position getLocation(double... distances) {
		if (positionsList == null || positionsList.size() != distances.length) {
			throw new LocationException("No es posible obtener la posicion.");
		}

		double[][] positions = toArray(positionsList);

		try {
			NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
					new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());

			Optimum optimum = solver.solve();

			double[] point = optimum.getPoint().toArray();

			return new Position(point[0], point[1]);	
		} catch (IllegalArgumentException e) {
			throw new LocationException("No hay suficiente información para calcular una posición.", e);
		}
	}

	/**
	 * Convierte una lista de posiciones a un arreglo con dos dimensiones.
	 * 
	 * @param positions Lista de posiciones.
	 * @return Arreglo con dos dimenciones con las posiciones.
	 */
	private double[][] toArray(List<Position> positions) {
		double[][] positionsArray = new double[positions.size()][2];
		for (int i = 0; i < positions.size(); i++) {
			Position coordinate = positions.get(i);
			positionsArray[i] = coordinate.toArray();
		}
		return positionsArray;
	}

}
