package com.starwars.quasar.domain.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkywalkerSatelite implements Satelite {

	private String name = "Skywalker";

	private Position position = new Position(100.0, -100.0);

}
