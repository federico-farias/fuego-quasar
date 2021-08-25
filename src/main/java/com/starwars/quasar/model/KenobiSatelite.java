package com.starwars.quasar.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KenobiSatelite implements Satelite {

	private String name = "Kenobi";

	private Position position = new Position(-500.0, -200.0);

}
