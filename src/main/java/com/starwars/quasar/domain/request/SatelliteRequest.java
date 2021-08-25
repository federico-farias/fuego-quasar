package com.starwars.quasar.domain.request;

import lombok.ToString;

import com.starwars.quasar.domain.exceptions.BusinessException;

import lombok.Getter;

@Getter
@ToString
public class SatelliteRequest {

	private String name;

	private Double distance;

	private String[] message;

	public SatelliteRequest(String name, Double distance, String[] message) {
		if (name == null) {
			throw new BusinessException("Se requiere especificar el nombre del satelite.");
		}
		if (distance == null) {
			throw new BusinessException(String.format("El satelite [%s] no recibio la distancia de la nave.", name));
		}
		if (message == null) {
			throw new BusinessException(String.format("El satelite [%s] no encontró recicio los mensajes.", name));
		}
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

}
