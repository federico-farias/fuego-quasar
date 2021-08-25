package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.exceptions.LocationException;
import com.starwars.quasar.model.Position;
import com.starwars.quasar.services.Locator;

public class LocatorTest {

	private Locator locator;

	@BeforeEach
	public void setUp() {
		this.locator = new Locator();
	}

	@Test
	public void itShouldCalculatePosition() {
		Position expectedPosition = new Position(
				-100.04391951008549, 
				75.45636245570131);
		
		List<Position> positions = Arrays.asList(
				new Position(-500.0, -200.0),
				new Position(100.0, -100.0),
				new Position(500.0, 100.0));
		
		double []distances = {
				505.0,
			    253.0, 
				628.0,
		};
		
		Position positionResult = this.locator.getLocation(positions, distances);
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
		
		assertThrows(LocationException.class, () -> {
			this.locator.getLocation(positions, distances);			
		});
	}

}
