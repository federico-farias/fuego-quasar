package com.starwars.quasar.domain.mapper;

import java.util.List;

import com.starwars.quasar.application.usecase.SatelliteRequest;

public class DistancesDataMapper {

	/**
	 * Método para obtener las distancias recibidas.
	 * 
	 * @param satellitesRequest Recepción de información de cada satélite.
	 * @return Arreglo de distancias recibidas.
	 */
	public double[] getDistances(List<SatelliteRequest> satellitesRequest) {
		double[] distances = new double[satellitesRequest.size()];
		for (int i = 0; i < satellitesRequest.size(); i++) {
			SatelliteRequest satelite = satellitesRequest.get(i);
			distances[i] = satelite.getDistance().doubleValue();
		}
		return distances;
	}

}
