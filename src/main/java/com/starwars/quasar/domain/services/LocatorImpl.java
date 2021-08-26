package com.starwars.quasar.domain.services;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starwars.quasar.domain.exceptions.LocationException;
import com.starwars.quasar.domain.model.Position;

public class LocatorImpl implements Locator {

	private List<Position> positionsList;

	public LocatorImpl(List<Position> positionsList) {
		this.positionsList = positionsList;
	}

	@Override
	public Position getLocation(double... distances) {
		if (positionsList.size() != distances.length) {
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
			throw new LocationException("No hay suficiente información para calcular una posicion.");
		}
	}

	private double[][] toArray(List<Position> positions) {
		double[][] positionsArray = new double[positions.size()][2];
		for (int i = 0; i < positions.size(); i++) {
			Position coordinate = positions.get(i);
			positionsArray[i] = coordinate.toArray();
		}
		return positionsArray;
	}

}
