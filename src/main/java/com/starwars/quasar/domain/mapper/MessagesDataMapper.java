package com.starwars.quasar.domain.mapper;

import java.util.List;

import com.starwars.quasar.application.usecase.SatelliteRequest;

public class MessagesDataMapper {

	/**
	 * Método para obtener los mensajes recibidos.
	 * 
	 * @param satellitesRequest Recepción de información de cada satélite.
	 * @return Arreglo con dos dimensiones de los mensajes recibidos.
	 */
	public String[][] getMessages(List<SatelliteRequest> satellitesRequest) {
		String[][] messages = new String[satellitesRequest.size()][];
		for (int i = 0; i < satellitesRequest.size(); i++) {
			SatelliteRequest satelite = satellitesRequest.get(i);
			messages[i] = satelite.getMessage();
		}
		return messages;
	}

}
