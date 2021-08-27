package com.starwars.quasar.application.http.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.application.http.schema.DistressMessageHttpRequest;
import com.starwars.quasar.application.http.schema.SatelliteHttpRequest;
import com.starwars.quasar.application.usecase.SatelliteRequest;

/**
 * Clase que se encarga de mapear las entradas los puertos para transformarlos a las entradas de negocio.
 * @author Federico Farias Sánchez
 *
 */
@Service
public class DistressMessageDataMapper {

	/**
	 * @param request Entrada del puerto 
	 * @return Entrada de negocio
	 */
	public List<SatelliteRequest> toRequest(DistressMessageHttpRequest request) {
		return request
				.getSatellites()
				.stream()
				.map(this::toRequest)
				.collect(Collectors.toList());
	}

	/**
	 * @param request Entrada del puerto 
	 * @return Entrada de negocio
	 */
	public SatelliteRequest toRequest(SatelliteHttpRequest satelite) {
		return new SatelliteRequest(
				satelite.getName(), 
				satelite.getDistance(), 
				satelite.getMessage());
	}

}
