package com.starwars.quasar.application.usecase;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DecryptedPositionResponse {
	
	private double x;

	private double y;

}
