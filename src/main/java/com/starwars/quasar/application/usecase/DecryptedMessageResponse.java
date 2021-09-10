package com.starwars.quasar.application.usecase;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DecryptedMessageResponse {
	
	private DecryptedPositionResponse position;

	private String message;

}
