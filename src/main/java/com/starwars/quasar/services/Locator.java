package com.starwars.quasar.services;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starwars.quasar.exceptions.LocationException;
import com.starwars.quasar.model.Position;

@Service
public class Locator {

	public Position getLocation(List<Position> positionsList, double... distances) {
		if (positionsList.size() != distances.length) {
			throw new LocationException("No es posible obtener la posicion.");
		}
		
		double[][] positions = toArray(positionsList);
		
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), 
				new LevenbergMarquardtOptimizer());
		
		Optimum optimum = solver.solve();
		
		double[] point = optimum.getPoint().toArray();
		
		return new Position(point[0], point[1]);
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
