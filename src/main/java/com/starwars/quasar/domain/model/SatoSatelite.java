package com.starwars.quasar.domain.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SatoSatelite implements Satelite {

	private String name = "Sato";

	private Position position = new Position(500.0, 100.0);

}
