package com.starwars.quasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Position {

	private double x;

	private double y;
	
	public double[] toArray() {
		return new double[] {
				this.x,
				this.y
		};
	}

}
