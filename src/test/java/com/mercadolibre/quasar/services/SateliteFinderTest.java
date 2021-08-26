package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;
import com.starwars.quasar.domain.services.SateliteFinder;

public class SateliteFinderTest {

	private SateliteFinder finder;
	
	private SatelliteRepository repository;

	@BeforeEach
	public void setUp() {
		this.repository = mock(SatelliteRepository.class);
		this.finder = new SateliteFinder(repository);
	}

	@Test
	public void itShouldFindTheSatelite() {
		when(this.repository.findByName("sato")).thenReturn(new Satelite("sato", new Position(0.1, 0.5)));
		Satelite expectedSatellite = new Satelite("sato", new Position(0.1, 0.5));
		Satelite satelite = this.finder.findByName("sato");
		assertEquals(satelite, expectedSatellite);
	}
	
	@Test
	public void itShouldThrowAnBusinessExceptionWhenCantFindASatelite() {
		assertThrows(BusinessException.class, () -> {			
			this.finder.findByName("sato");
		});
	}

}
