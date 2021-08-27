package com.starwars.quasar.domain.services;

/**
 * Contrato con la firma sugerida para implementar el descifrado del mensaje de
 * la nave.
 * 
 * @author Federico Farias Sánchez
 *
 */
public interface MessageDecryptor {

	/**
	 * 
	 * @param messages El mensaje tal cual es recibido en cada satélite.
	 * @return El mensaje tal cual lo genera el emisor del mensaje.
	 */
	String getMessage(String[]... messages);

}
