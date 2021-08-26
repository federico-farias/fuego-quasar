package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;
import com.starwars.quasar.domain.request.SatelliteRequest;
import com.starwars.quasar.domain.services.SatellitesFinder;

public class SatellitesFinderTest {

	private SatelliteRepository repository;

	private SatellitesFinder finder;

	@BeforeEach
	public void setUp() {
		this.repository = mock(SatelliteRepository.class);
		this.finder = new SatellitesFinder(repository);
	}

	@Test
	public void itShouldFindTheSatelite() {
		when(this.repository.findByName("sato")).thenReturn(new Satelite("sato", new Position(0.3, 0.5)));
		List<SatelliteRequest> satellites = Arrays
				.asList(new SatelliteRequest("sato", 100.0, new String[] { "mi", "mensaje" }));
		List<Satelite> result = this.finder.findByName(satellites);
		assertEquals(result.size(), 1);
	}
	
	@Test
	public void itShouldThrowAnBusinessExceptionWhenCantFindASatelite() {
		assertThrows(BusinessException.class, () -> {
			List<SatelliteRequest> satellites = Arrays
					.asList(new SatelliteRequest("sato", 100.0, new String[] { "mi", "mensaje" }));
			this.finder.findByName(satellites);
		});
	}

}
