package com.starwars.quasar.application.http.schema;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TopSecretHttpResponse {

	private PositionHttpResponse position;

	private String message;

}
