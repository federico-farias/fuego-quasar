package com.starwars.quasar.domain.services;

import com.starwars.quasar.domain.model.Position;

public interface InteligenceService {
	
	Position getLocation(double... distances);

	String getMessage(String[]... messages);

}
