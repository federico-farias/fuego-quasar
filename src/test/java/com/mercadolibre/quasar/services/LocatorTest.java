package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.exceptions.LocationException;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.services.impl.LocatorImpl;

public class LocatorTest {

	@Test
	public void itShouldCalculatePosition() {
		Position expectedPosition = new Position(
				-100.04391951008549, 
				75.45636245570131);
		
		double []distances = {
				505.0,
			    253.0, 
				628.0,
		};
		List<Position> positions = Arrays.asList(
				new Position(-500.0, -200.0),
				new Position(100.0, -100.0),
				new Position(500.0, 100.0));
		
		LocatorImpl locator = new LocatorImpl(positions);
		
		Position positionResult = locator.getLocation(distances);
		
		assertEquals(positionResult, expectedPosition);
	}
	
	@Test
	public void itShouldNotCalculatePositionWhenPositionsNumberDoesNotMatchWithDistancesNumber() {
		List<Position> positions = Arrays.asList(
				new Position(-500.0, -200.0),
				new Position(100.0, -100.0));
		
		double []distances = {
				505.0
		};
		
		final LocatorImpl locator = new LocatorImpl(positions);
		
		assertThrows(LocationException.class, () -> {
			locator.getLocation(distances);			
		});
	}

}
