package com.starwars.quasar.services;

import com.starwars.quasar.model.Position;

public interface InteligenceService {
	
	Position getLocation(double... distances);

	String getMessage(String[]... messages);

}
