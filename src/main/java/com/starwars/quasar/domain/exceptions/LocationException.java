package com.starwars.quasar.domain.exceptions;

public class LocationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public LocationException(String message) {
		super(message);
	}

}
