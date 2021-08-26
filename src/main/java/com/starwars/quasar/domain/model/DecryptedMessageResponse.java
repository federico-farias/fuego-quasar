package com.starwars.quasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DecryptedMessageResponse {
	
	private PositionResponse position;

	private String message;

}
